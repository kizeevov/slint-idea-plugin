// This is a generated file. Not intended for manual editing.
package dev.slint.ideaplugin.lang.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import dev.slint.ideaplugin.lang.psi.impl.*;

public interface SlintElementTypes {

  IElementType FIELD_DECLARATION_BLOCK = new SlintElementType("FIELD_DECLARATION_BLOCK");
  IElementType FIELD_TYPE = new SlintElementType("FIELD_TYPE");
  IElementType SINGLE_TYPE = new SlintElementType("SINGLE_TYPE");
  IElementType STRUCT_DECLARATION = new SlintElementType("STRUCT_DECLARATION");
  IElementType STRUCT_FIELD_DECLARATION = new SlintElementType("STRUCT_FIELD_DECLARATION");

  IElementType ANIMATE = new SlintTokenType("animate");
  IElementType AT = new SlintTokenType("@");
  IElementType CALLBACK = new SlintTokenType("callback");
  IElementType COLON = new SlintTokenType(":");
  IElementType COLONEQ = new SlintTokenType(":=");
  IElementType COMMA = new SlintTokenType(",");
  IElementType COMPONENT = new SlintTokenType("component");
  IElementType DOC_COMMENT = new SlintTokenType("DOC_COMMENT");
  IElementType DOT = new SlintTokenType(".");
  IElementType ENUM = new SlintTokenType("enum");
  IElementType EQ = new SlintTokenType("=");
  IElementType EXCL = new SlintTokenType("!");
  IElementType EXPORT = new SlintTokenType("export");
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
  IElementType PARAMETER_LITERAL = new SlintTokenType("PARAMETER_LITERAL");
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
  IElementType TYPE_LITERAL = new SlintTokenType("TYPE_LITERAL");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == FIELD_DECLARATION_BLOCK) {
        return new SlintFieldDeclarationBlockImpl(node);
      }
      else if (type == FIELD_TYPE) {
        return new SlintFieldTypeImpl(node);
      }
      else if (type == SINGLE_TYPE) {
        return new SlintSingleTypeImpl(node);
      }
      else if (type == STRUCT_DECLARATION) {
        return new SlintStructDeclarationImpl(node);
      }
      else if (type == STRUCT_FIELD_DECLARATION) {
        return new SlintStructFieldDeclarationImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
