// This is a generated file. Not intended for manual editing.
package dev.slint.ideaplugin.lang.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import dev.slint.ideaplugin.lang.psi.impl.*;

public interface SlintElementTypes {

  IElementType ALIGNMENT_EXPRESSION = new SlintElementType("ALIGNMENT_EXPRESSION");
  IElementType ASSIGNMENT_EXPRESSION = new SlintElementType("ASSIGNMENT_EXPRESSION");
  IElementType BINARY_EXPRESSION = new SlintElementType("BINARY_EXPRESSION");
  IElementType BOOLEAN_EXPRESSION = new SlintElementType("BOOLEAN_EXPRESSION");
  IElementType CALL_EXPRESSION = new SlintElementType("CALL_EXPRESSION");
  IElementType COLOR_EXPRESSION = new SlintElementType("COLOR_EXPRESSION");
  IElementType COMPONENT_DECLARATION = new SlintElementType("COMPONENT_DECLARATION");
  IElementType COMPONENT_FIELD_DECLARATION_BLOCK = new SlintElementType("COMPONENT_FIELD_DECLARATION_BLOCK");
  IElementType DURATION_EXPRESSION = new SlintElementType("DURATION_EXPRESSION");
  IElementType EXPRESSION = new SlintElementType("EXPRESSION");
  IElementType FIELD_TYPE = new SlintElementType("FIELD_TYPE");
  IElementType IMPORT_DECLARATION = new SlintElementType("IMPORT_DECLARATION");
  IElementType IMPORT_ELEMENT_DECLARATION_BLOCK = new SlintElementType("IMPORT_ELEMENT_DECLARATION_BLOCK");
  IElementType IMPORT_PATH_DECLARATION = new SlintElementType("IMPORT_PATH_DECLARATION");
  IElementType LENGTH_EXPRESSION = new SlintElementType("LENGTH_EXPRESSION");
  IElementType MATHEMATICAL_OPERATORS = new SlintElementType("MATHEMATICAL_OPERATORS");
  IElementType NUMERIC_EXPRESSION = new SlintElementType("NUMERIC_EXPRESSION");
  IElementType OPERATORS = new SlintElementType("OPERATORS");
  IElementType PROPERTY_EXPRESSION = new SlintElementType("PROPERTY_EXPRESSION");
  IElementType STRING_EXPRESSION = new SlintElementType("STRING_EXPRESSION");
  IElementType STRUCT_DECLARATION = new SlintElementType("STRUCT_DECLARATION");
  IElementType STRUCT_FIELD_DECLARATION = new SlintElementType("STRUCT_FIELD_DECLARATION");
  IElementType STRUCT_FIELD_DECLARATION_BLOCK = new SlintElementType("STRUCT_FIELD_DECLARATION_BLOCK");
  IElementType STRUCT_FIELD_NAME = new SlintElementType("STRUCT_FIELD_NAME");
  IElementType TERNARY_EXPRESSION = new SlintElementType("TERNARY_EXPRESSION");
  IElementType UNARY_EXPRESSION = new SlintElementType("UNARY_EXPRESSION");
  IElementType UNARY_OPERATORS = new SlintElementType("UNARY_OPERATORS");

  IElementType ADDITION = new SlintTokenType("+");
  IElementType ANIMATE = new SlintTokenType("animate");
  IElementType AT = new SlintTokenType("@");
  IElementType CALLBACK = new SlintTokenType("callback");
  IElementType COLON = new SlintTokenType(":");
  IElementType COLOR_LITERAL = new SlintTokenType("COLOR_LITERAL");
  IElementType COMMA = new SlintTokenType(",");
  IElementType COMPONENT = new SlintTokenType("component");
  IElementType DIVIDE = new SlintTokenType("/");
  IElementType DOC_COMMENT = new SlintTokenType("DOC_COMMENT");
  IElementType DOT = new SlintTokenType(".");
  IElementType DURATION_LITERAL = new SlintTokenType("DURATION_LITERAL");
  IElementType ENUM = new SlintTokenType("enum");
  IElementType EQ = new SlintTokenType("=");
  IElementType EXCL = new SlintTokenType("!");
  IElementType EXPORT = new SlintTokenType("export");
  IElementType FLOOR_DIVIDE = new SlintTokenType("\\");
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
  IElementType LENGTH_LITERAL = new SlintTokenType("LENGTH_LITERAL");
  IElementType LINE_COMMENT = new SlintTokenType("LINE_COMMENT");
  IElementType LPAREN = new SlintTokenType("(");
  IElementType MODULO = new SlintTokenType("%");
  IElementType MULTIPLY = new SlintTokenType("*");
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
  IElementType SEMICOLON = new SlintTokenType(";");
  IElementType STATES = new SlintTokenType("states");
  IElementType STRING_LITERAL = new SlintTokenType("STRING_LITERAL");
  IElementType STRUCT = new SlintTokenType("struct");
  IElementType SUBTRACTION = new SlintTokenType("-");
  IElementType TRANSITIONS = new SlintTokenType("transitions");
  IElementType TYPE_LITERAL = new SlintTokenType("TYPE_LITERAL");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ALIGNMENT_EXPRESSION) {
        return new SlintAlignmentExpressionImpl(node);
      }
      else if (type == ASSIGNMENT_EXPRESSION) {
        return new SlintAssignmentExpressionImpl(node);
      }
      else if (type == BINARY_EXPRESSION) {
        return new SlintBinaryExpressionImpl(node);
      }
      else if (type == BOOLEAN_EXPRESSION) {
        return new SlintBooleanExpressionImpl(node);
      }
      else if (type == CALL_EXPRESSION) {
        return new SlintCallExpressionImpl(node);
      }
      else if (type == COLOR_EXPRESSION) {
        return new SlintColorExpressionImpl(node);
      }
      else if (type == COMPONENT_DECLARATION) {
        return new SlintComponentDeclarationImpl(node);
      }
      else if (type == COMPONENT_FIELD_DECLARATION_BLOCK) {
        return new SlintComponentFieldDeclarationBlockImpl(node);
      }
      else if (type == DURATION_EXPRESSION) {
        return new SlintDurationExpressionImpl(node);
      }
      else if (type == FIELD_TYPE) {
        return new SlintFieldTypeImpl(node);
      }
      else if (type == IMPORT_DECLARATION) {
        return new SlintImportDeclarationImpl(node);
      }
      else if (type == IMPORT_ELEMENT_DECLARATION_BLOCK) {
        return new SlintImportElementDeclarationBlockImpl(node);
      }
      else if (type == IMPORT_PATH_DECLARATION) {
        return new SlintImportPathDeclarationImpl(node);
      }
      else if (type == LENGTH_EXPRESSION) {
        return new SlintLengthExpressionImpl(node);
      }
      else if (type == MATHEMATICAL_OPERATORS) {
        return new SlintMathematicalOperatorsImpl(node);
      }
      else if (type == NUMERIC_EXPRESSION) {
        return new SlintNumericExpressionImpl(node);
      }
      else if (type == OPERATORS) {
        return new SlintOperatorsImpl(node);
      }
      else if (type == PROPERTY_EXPRESSION) {
        return new SlintPropertyExpressionImpl(node);
      }
      else if (type == STRING_EXPRESSION) {
        return new SlintStringExpressionImpl(node);
      }
      else if (type == STRUCT_DECLARATION) {
        return new SlintStructDeclarationImpl(node);
      }
      else if (type == STRUCT_FIELD_DECLARATION) {
        return new SlintStructFieldDeclarationImpl(node);
      }
      else if (type == STRUCT_FIELD_DECLARATION_BLOCK) {
        return new SlintStructFieldDeclarationBlockImpl(node);
      }
      else if (type == STRUCT_FIELD_NAME) {
        return new SlintStructFieldNameImpl(node);
      }
      else if (type == TERNARY_EXPRESSION) {
        return new SlintTernaryExpressionImpl(node);
      }
      else if (type == UNARY_EXPRESSION) {
        return new SlintUnaryExpressionImpl(node);
      }
      else if (type == UNARY_OPERATORS) {
        return new SlintUnaryOperatorsImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
