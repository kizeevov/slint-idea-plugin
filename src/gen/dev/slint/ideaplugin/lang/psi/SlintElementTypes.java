// This is a generated file. Not intended for manual editing.
package dev.slint.ideaplugin.lang.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import dev.slint.ideaplugin.lang.psi.impl.*;

public interface SlintElementTypes {

  IElementType KEY_VALUE = new SlintElementType("KEY_VALUE");

  IElementType ANIMATE = new SlintTokenType("animate");
  IElementType AT = new SlintTokenType("@");
  IElementType ATAT = new SlintTokenType("@@");
  IElementType CALLBACK = new SlintTokenType("callback");
  IElementType COLON = new SlintTokenType(":");
  IElementType COMMA = new SlintTokenType(",");
  IElementType COMPONENT = new SlintTokenType("component");
  IElementType DOC_COMMENT = new SlintTokenType("DOC_COMMENT");
  IElementType DOT = new SlintTokenType(".");
  IElementType ENUM = new SlintTokenType("enum");
  IElementType EQ = new SlintTokenType("=");
  IElementType EXCL = new SlintTokenType("!");
  IElementType EXPORT = new SlintTokenType("export");
  IElementType EXPRESSION = new SlintTokenType("Expression");
  IElementType FOR = new SlintTokenType("for");
  IElementType FROM = new SlintTokenType("from");
  IElementType FUNCTION = new SlintTokenType("function");
  IElementType GLOBAL = new SlintTokenType("global");
  IElementType IDENTIFIER = new SlintTokenType("IDENTIFIER");
  IElementType IF = new SlintTokenType("if");
  IElementType IMPORT = new SlintTokenType("import");
  IElementType IN = new SlintTokenType("in");
  IElementType INHERITS = new SlintTokenType("inherits");
  IElementType IN_OUNT = new SlintTokenType("in-out");
  IElementType LBRACE = new SlintTokenType("{");
  IElementType LBRACKET = new SlintTokenType("[");
  IElementType LINE_COMMENT = new SlintTokenType("LINE_COMMENT");
  IElementType LPAREN = new SlintTokenType("(");
  IElementType NUMERIC_LITERAL = new SlintTokenType("NUMERIC_LITERAL");
  IElementType OUT = new SlintTokenType("out");
  IElementType PRIVATE = new SlintTokenType("private");
  IElementType PROPERTY = new SlintTokenType("property");
  IElementType PUBLIC = new SlintTokenType("public");
  IElementType PURE = new SlintTokenType("pure");
  IElementType QUEST = new SlintTokenType("?");
  IElementType RBRACE = new SlintTokenType("}");
  IElementType RBRACKET = new SlintTokenType("]");
  IElementType RETURN = new SlintTokenType("return");
  IElementType RPAREN = new SlintTokenType(")");
  IElementType STATES = new SlintTokenType("states");
  IElementType STRING_LITERAL = new SlintTokenType("STRING_LITERAL");
  IElementType STRUCT = new SlintTokenType("struct");
  IElementType TRANSITIONS = new SlintTokenType("transitions");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == KEY_VALUE) {
        return new SlintKeyValueImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
