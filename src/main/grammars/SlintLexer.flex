package dev.slint.ideaplugin.lang.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static dev.slint.ideaplugin.lang.psi.SlintElementTypes.*;

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

WHITE_SPACE=\s+

STRING_LITERAL=('([^'\\]|\\.)*'|\"([^\"\\]|\\.)*\")
NUMERIC_LITERAL=-?[0-9]+(\.[0-9]+)?
COLOR_LITERAL=#([A-Fa-f0-9]{6,8}|[A-Fa-f0-9]{3,4})
IDENTIFIER=[a-zA-Z][a-zA-Z0-9\-_]*
PROPERTY_IDENTIFIER=([a-zA-Z][a-zA-Z0-9\-_]*[\\.])+[a-zA-Z0-9\-_]*
WHITE_SPACE=[ \t\n\x0B\f\r]+
LINE_COMMENT="//".*
DOC_COMMENT="/"\*([^*]|[\r\n]|(\*+([^*/]|[\r\n])))*\*+"/"

%%
<YYINITIAL> {
  {WHITE_SPACE}               { return WHITE_SPACE; }

  "import"                    { return IMPORT; }
  "from"                      { return FROM; }
  "export"                    { return EXPORT; }
  "struct"                    { return STRUCT; }
  "enum"                      { return ENUM; }
  "global"                    { return GLOBAL; }
  "component"                 { return COMPONENT; }
  "inherits"                  { return INHERITS; }
  "property"                  { return PROPERTY; }
  "callback"                  { return CALLBACK; }
  "animate"                   { return ANIMATE; }
  "states"                    { return STATES; }
  "transitions"               { return TRANSITIONS; }
  "private"                   { return PRIVATE; }
  "public"                    { return PUBLIC; }
  "protected"                 { return PROTECTED; }
  "pure"                      { return PURE; }
  "function"                  { return FUNCTION; }
  "in"                        { return IN; }
  "out"                       { return OUT; }
  "in-out"                    { return IN_OUNT; }
  "if"                        { return IF; }
  "else"                      { return ELSE; }
  "else if"                   { return ELSE_IF; }
  "for"                       { return FOR; }
  "return"                    { return RETURN; }
  "{"                         { return LBRACE; }
  "}"                         { return RBRACE; }
  "("                         { return LPAREN; }
  ")"                         { return RPAREN; }
  "["                         { return LBRACKET; }
  "]"                         { return RBRACKET; }
  "="                         { return EQ; }
  ";"                         { return SEMICOLON; }
  "?"                         { return QUEST; }
  ","                         { return COMMA; }
  "+"                         { return ADDITION; }
  "-"                         { return SUBTRACTION; }
  "*"                         { return MULTIPLY; }
  "/"                         { return DIVIDE; }
  "%"                         { return MODULO; }
  "\\"                        { return FLOOR_DIVIDE; }
  "-="                        { return MINUS_EQ; }
  "+="                        { return PLUS_EQ; }
  "/="                        { return DIV_EQ; }
  "*="                        { return MUL_EQ; }
  "&&"                        { return AND; }
  "||"                        { return OR; }
  "!"                         { return NOT; }
  "=="                        { return EQUAL; }
  "!="                        { return NOT_EQUAL; }
  "<"                         { return LESS_THAN; }
  "<="                        { return LESS_THAN_OR_EQUAL; }
  ">"                         { return GREATER_THAN; }
  ">="                        { return GREATER_THAN_OR_EQUAL; }

  {STRING_LITERAL}            { return STRING_LITERAL; }
  {NUMERIC_LITERAL}           { return NUMERIC_LITERAL; }
  {COLOR_LITERAL}             { return COLOR_LITERAL; }
  {IDENTIFIER}                { return IDENTIFIER; }
  {PROPERTY_IDENTIFIER}       { return PROPERTY_IDENTIFIER; }
  {WHITE_SPACE}               { return WHITE_SPACE; }
  {LINE_COMMENT}              { return LINE_COMMENT; }
  {DOC_COMMENT}               { return DOC_COMMENT; }

}

[^] { return BAD_CHARACTER; }
