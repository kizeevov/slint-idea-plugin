// This is a generated file. Not intended for manual editing.
package dev.slint.ideaplugin.lang.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import dev.slint.ideaplugin.lang.psi.impl.SlintKeyValueImpl;
import dev.slint.ideaplugin.lang.psi.impl.*;

public interface SlintElementTypes {

  IElementType KEY_VALUE = new SlintElementType("KEY_VALUE");

  IElementType AT = new SlintTokenType("@");
  IElementType ATAT = new SlintTokenType("@@");
  IElementType COLON = new SlintTokenType(":");
  IElementType COMMA = new SlintTokenType(",");
  IElementType DOC_COMMENT = new SlintTokenType("DOC_COMMENT");
  IElementType DOT = new SlintTokenType(".");
  IElementType EQ = new SlintTokenType("=");
  IElementType EXCL = new SlintTokenType("!");
  IElementType EXPORT = new SlintTokenType("export");
  IElementType EXPRESSION = new SlintTokenType("Expression");
  IElementType IDENTIFIER = new SlintTokenType("IDENTIFIER");
  IElementType IMPORT = new SlintTokenType("import");
  IElementType LBRACE = new SlintTokenType("{");
  IElementType LBRACKET = new SlintTokenType("[");
  IElementType LINE_COMMENT = new SlintTokenType("LINE_COMMENT");
  IElementType LPAREN = new SlintTokenType("(");
  IElementType NUMERIC_LITERAL = new SlintTokenType("NUMERIC_LITERAL");
  IElementType QUEST = new SlintTokenType("?");
  IElementType RBRACE = new SlintTokenType("}");
  IElementType RBRACKET = new SlintTokenType("]");
  IElementType RPAREN = new SlintTokenType(")");
  IElementType STRING_LITERAL = new SlintTokenType("STRING_LITERAL");

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
