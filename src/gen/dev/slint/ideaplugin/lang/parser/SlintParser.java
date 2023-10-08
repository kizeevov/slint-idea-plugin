// This is a generated file. Not intended for manual editing.
package dev.slint.ideaplugin.lang.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static dev.slint.ideaplugin.lang.psi.SlintElementTypes.*;
import static dev.slint.ideaplugin.lang.parser.SlintParserUtil.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class SlintParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType root_, PsiBuilder builder_) {
    parseLight(root_, builder_);
    return builder_.getTreeBuilt();
  }

  public void parseLight(IElementType root_, PsiBuilder builder_) {
    boolean result_;
    builder_ = adapt_builder_(root_, builder_, this, null);
    Marker marker_ = enter_section_(builder_, 0, _COLLAPSE_, null);
    result_ = parse_root_(root_, builder_);
    exit_section_(builder_, 0, marker_, root_, result_, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType root_, PsiBuilder builder_) {
    return parse_root_(root_, builder_, 0);
  }

  static boolean parse_root_(IElementType root_, PsiBuilder builder_, int level_) {
    return Schema(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // StructDeclaration
  static boolean Declaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Declaration")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = StructDeclaration(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, SlintParser::Declaration_recover);
    return result_;
  }

  /* ********************************************************** */
  // !TopLevelKeywords
  static boolean Declaration_recover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Declaration_recover")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !TopLevelKeywords(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // '{' StructFieldDeclarationBlockItem* '}' {
  // }
  public static boolean FieldDeclarationBlock(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FieldDeclarationBlock")) return false;
    if (!nextTokenIs(builder_, LBRACE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LBRACE);
    result_ = result_ && FieldDeclarationBlock_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RBRACE);
    result_ = result_ && FieldDeclarationBlock_3(builder_, level_ + 1);
    exit_section_(builder_, marker_, FIELD_DECLARATION_BLOCK, result_);
    return result_;
  }

  // StructFieldDeclarationBlockItem*
  private static boolean FieldDeclarationBlock_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FieldDeclarationBlock_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!StructFieldDeclarationBlockItem(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "FieldDeclarationBlock_1", pos_)) break;
    }
    return true;
  }

  // {
  // }
  private static boolean FieldDeclarationBlock_3(PsiBuilder builder_, int level_) {
    return true;
  }

  /* ********************************************************** */
  // SingleType
  public static boolean FieldType(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FieldType")) return false;
    if (!nextTokenIs(builder_, IDENTIFIER)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = SingleType(builder_, level_ + 1);
    exit_section_(builder_, marker_, FIELD_TYPE, result_);
    return result_;
  }

  /* ********************************************************** */
  // IDENTIFIER
  static boolean Identifier(PsiBuilder builder_, int level_) {
    return consumeToken(builder_, IDENTIFIER);
  }

  /* ********************************************************** */
  // Declaration*
  static boolean Schema(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Schema")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!Declaration(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "Schema", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // Identifier
  public static boolean SingleType(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SingleType")) return false;
    if (!nextTokenIs(builder_, IDENTIFIER)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = Identifier(builder_, level_ + 1);
    exit_section_(builder_, marker_, SINGLE_TYPE, result_);
    return result_;
  }

  /* ********************************************************** */
  // EXPORT? STRUCT Identifier FieldDeclarationBlock
  public static boolean StructDeclaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StructDeclaration")) return false;
    if (!nextTokenIs(builder_, "<struct declaration>", EXPORT, STRUCT)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, STRUCT_DECLARATION, "<struct declaration>");
    result_ = StructDeclaration_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, STRUCT);
    result_ = result_ && Identifier(builder_, level_ + 1);
    result_ = result_ && FieldDeclarationBlock(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // EXPORT?
  private static boolean StructDeclaration_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StructDeclaration_0")) return false;
    consumeToken(builder_, EXPORT);
    return true;
  }

  /* ********************************************************** */
  // PARAMETER_LITERAL FieldType ','
  public static boolean StructFieldDeclaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StructFieldDeclaration")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, STRUCT_FIELD_DECLARATION, "<struct field declaration>");
    result_ = consumeToken(builder_, PARAMETER_LITERAL);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, FieldType(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, COMMA) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, SlintParser::UntilNewLine_recover);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // StructFieldDeclaration
  static boolean StructFieldDeclarationBlockItem(PsiBuilder builder_, int level_) {
    return StructFieldDeclaration(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // EXPORT | STRUCT | RBRACE
  static boolean TopLevelKeywords(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TopLevelKeywords")) return false;
    boolean result_;
    result_ = consumeToken(builder_, EXPORT);
    if (!result_) result_ = consumeToken(builder_, STRUCT);
    if (!result_) result_ = consumeToken(builder_, RBRACE);
    return result_;
  }

  /* ********************************************************** */
  // !<<isNewLine>>
  static boolean UntilNewLine_recover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "UntilNewLine_recover")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !isNewLine(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

}
