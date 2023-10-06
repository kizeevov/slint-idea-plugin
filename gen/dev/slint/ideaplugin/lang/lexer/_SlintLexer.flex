package dev.slint.ideaplugin.lang.lexer;

import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;

%%

%{
  public _SlintLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class _SlintLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

EOL=\R
WHITE_SPACE=\s+

STRING_LITERAL=('([^'\\]|\\.)*'|\"([^\"\\]|\\.)*\")
NUMERIC_LITERAL=-?[0-9]+(\.[0-9]+)?
IDENTIFIER=[a-zA-Z][a-zA-Z0-9\-_]*
WHITE_SPACE=[ \t\n\x0B\f\r]+
LINE_COMMENT="//".*
DOC_COMMENT="/"\*.*\*"/"

%%
<YYINITIAL> {
  {WHITE_SPACE}           { return WHITE_SPACE; }

  "import"                { return IMPORT; }
  "export"                { return EXPORT; }
  "{"                     { return LBRACE; }
  "}"                     { return RBRACE; }
  "("                     { return LPAREN; }
  ")"                     { return RPAREN; }
  "["                     { return LBRACKET; }
  "]"                     { return RBRACKET; }
  "="                     { return EQ; }
  "."                     { return DOT; }
  ":"                     { return COLON; }
  "?"                     { return QUEST; }
  "!"                     { return EXCL; }
  "@"                     { return AT; }
  "@@"                    { return ATAT; }
  ","                     { return COMMA; }
  "Expression"            { return EXPRESSION; }

  {STRING_LITERAL}        { return STRING_LITERAL; }
  {NUMERIC_LITERAL}       { return NUMERIC_LITERAL; }
  {IDENTIFIER}            { return IDENTIFIER; }
  {WHITE_SPACE}           { return WHITE_SPACE; }
  {LINE_COMMENT}          { return LINE_COMMENT; }
  {DOC_COMMENT}           { return DOC_COMMENT; }

}

[^] { return BAD_CHARACTER; }