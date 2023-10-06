package dev.slint.ideaplugin.lang.lexer;

import com.intellij.psi.tree.IElementType;
import com.intellij.openapi.util.text.StringUtil;
import static com.intellij.psi.TokenType.*;

%%

%{
  public _SlintLexer() {
    this((java.io.Reader)null);
  }

  private void handleNewLine() {
      if (yystate() == DECL && StringUtil.containsLineBreak(yytext())) {
          yybegin(YYINITIAL);
      }
  }
%}

%class _SlintLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType

EOL_WS           = \n | \r | \r\n
LINE_WS          = [\ \t]
WHITE_SPACE_CHAR = {EOL_WS} | {LINE_WS}
WHITE_SPACE      = {WHITE_SPACE_CHAR}+
DIGIT            = [:digit:]

NAME_START       = [a-zA-Z]
NAME_BODY        = [a-zA-Z0-9\-_]
IDENTIFIER       = {NAME_START} ({NAME_BODY})*

STRING_LITERAL   = \"([^\\\"\r\n]|\\[^\r\n])*\"?
NUMERIC_LITERAL  = "-"? {DIGIT}+ ("." {DIGIT}+)?

DOC_COMMENT = "/"\*.*\*"/"
LINE_COMMENT = "//" .*

%state DECL, BLOCK

%%

<YYINITIAL> {
    "import"            { yybegin(DECL); return IMPORT; }
    "export"             { yybegin(DECL); return EXPORT; }
}

"{"                { yybegin(BLOCK); return LBRACE; }
"}"                { yybegin(YYINITIAL); return RBRACE; }
"("                { return LPAREN; }
")"                { return RPAREN; }
"["                { return LBRACKET; }
"]"                { return RBRACKET; }
"="                { return EQ; }
"."                { return DOT; }
":"                { return COLON; }
"?"                { return QUEST; }
"!"                { return EXCL; }
"@"                { return AT; }
"@@"               { return ATAT; }
","                { return COMMA; }

{IDENTIFIER}       { return IDENTIFIER; }
{NUMERIC_LITERAL}  { return NUMERIC_LITERAL; }
{STRING_LITERAL}   { return STRING_LITERAL; }
{WHITE_SPACE}      { handleNewLine(); return WHITE_SPACE; }

{DOC_COMMENT}      { return DOC_COMMENT; }
{LINE_COMMENT}     { return LINE_COMMENT; }

[^]                { return BAD_CHARACTER; }