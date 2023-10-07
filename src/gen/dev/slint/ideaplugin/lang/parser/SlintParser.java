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
    return functionName(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // IDENTIFIER
  static boolean Identifier(PsiBuilder builder_, int level_) {
    return consumeToken(builder_, IDENTIFIER);
  }

  /* ********************************************************** */
  // Identifier '=' Expression
  public static boolean KeyValue(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "KeyValue")) return false;
    if (!nextTokenIs(builder_, IDENTIFIER)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, KEY_VALUE, null);
    result_ = Identifier(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, consumeTokens(builder_, -1, EQ, EXPRESSION));
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // IDENTIFIER
  static boolean functionName(PsiBuilder builder_, int level_) {
    return consumeToken(builder_, IDENTIFIER);
  }

}
