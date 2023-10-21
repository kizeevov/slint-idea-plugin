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
    builder_ = adapt_builder_(root_, builder_, this, EXTENDS_SETS_);
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

  public static final TokenSet[] EXTENDS_SETS_ = new TokenSet[] {
    create_token_set_(ALIGNMENT_EXPRESSION, ASSIGNMENT_EXPRESSION, BINARY_EXPRESSION, BOOLEAN_EXPRESSION,
      CALL_EXPRESSION, COLOR_EXPRESSION, DURATION_EXPRESSION, EXPRESSION,
      LENGTH_EXPRESSION, NUMERIC_EXPRESSION, PROPERTY_EXPRESSION, STRING_EXPRESSION,
      TERNARY_EXPRESSION, UNARY_EXPRESSION),
  };

  /* ********************************************************** */
  // 'black'
  //     | 'silver'
  //     | 'gray'
  //     | 'white'
  //     | 'maroon'
  //     | 'red'
  //     | 'purple'
  //     | 'fuchsia'
  //     | 'green'
  //     | 'lime'
  //     | 'olive'
  //     | 'yellow'
  //     | 'navy'
  //     | 'blue'
  //     | 'teal'
  //     | 'aqua'
  static boolean BasicColors(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "BasicColors")) return false;
    boolean result_;
    result_ = consumeToken(builder_, "black");
    if (!result_) result_ = consumeToken(builder_, "silver");
    if (!result_) result_ = consumeToken(builder_, "gray");
    if (!result_) result_ = consumeToken(builder_, "white");
    if (!result_) result_ = consumeToken(builder_, "maroon");
    if (!result_) result_ = consumeToken(builder_, "red");
    if (!result_) result_ = consumeToken(builder_, "purple");
    if (!result_) result_ = consumeToken(builder_, "fuchsia");
    if (!result_) result_ = consumeToken(builder_, "green");
    if (!result_) result_ = consumeToken(builder_, "lime");
    if (!result_) result_ = consumeToken(builder_, "olive");
    if (!result_) result_ = consumeToken(builder_, "yellow");
    if (!result_) result_ = consumeToken(builder_, "navy");
    if (!result_) result_ = consumeToken(builder_, "blue");
    if (!result_) result_ = consumeToken(builder_, "teal");
    if (!result_) result_ = consumeToken(builder_, "aqua");
    return result_;
  }

  /* ********************************************************** */
  // ANIMATE ComponentDeclaration
  static boolean ComponentAnimateDeclaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ComponentAnimateDeclaration")) return false;
    if (!nextTokenIs(builder_, ANIMATE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ANIMATE);
    result_ = result_ && ComponentDeclaration(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // Identifier '=>' ComponentCallbackDeclarationBlock
  static boolean ComponentCallbackDeclaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ComponentCallbackDeclaration")) return false;
    if (!nextTokenIs(builder_, IDENTIFIER)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = Identifier(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, "=>");
    result_ = result_ && ComponentCallbackDeclarationBlock(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // LBRACE ComponentCallbackDeclarationBlockItems* RBRACE
  static boolean ComponentCallbackDeclarationBlock(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ComponentCallbackDeclarationBlock")) return false;
    if (!nextTokenIs(builder_, LBRACE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LBRACE);
    result_ = result_ && ComponentCallbackDeclarationBlock_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RBRACE);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ComponentCallbackDeclarationBlockItems*
  private static boolean ComponentCallbackDeclarationBlock_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ComponentCallbackDeclarationBlock_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!ComponentCallbackDeclarationBlockItems(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "ComponentCallbackDeclarationBlock_1", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // Expression SEMICOLON
  static boolean ComponentCallbackDeclarationBlockItems(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ComponentCallbackDeclarationBlockItems")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = Expression(builder_, level_ + 1, -1);
    result_ = result_ && consumeToken(builder_, SEMICOLON);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // '<=>' PropertyExpression
  static boolean ComponentCallbackPropertyBindingDeclaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ComponentCallbackPropertyBindingDeclaration")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, "<=>");
    result_ = result_ && PropertyExpression(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // CALLBACK Identifier (ComponentCallbackPropertyReturnDeclaration | ComponentCallbackPropertyBindingDeclaration)? ';'
  static boolean ComponentCallbackPropertyDeclaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ComponentCallbackPropertyDeclaration")) return false;
    if (!nextTokenIs(builder_, CALLBACK)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, CALLBACK);
    result_ = result_ && Identifier(builder_, level_ + 1);
    result_ = result_ && ComponentCallbackPropertyDeclaration_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, SEMICOLON);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (ComponentCallbackPropertyReturnDeclaration | ComponentCallbackPropertyBindingDeclaration)?
  private static boolean ComponentCallbackPropertyDeclaration_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ComponentCallbackPropertyDeclaration_2")) return false;
    ComponentCallbackPropertyDeclaration_2_0(builder_, level_ + 1);
    return true;
  }

  // ComponentCallbackPropertyReturnDeclaration | ComponentCallbackPropertyBindingDeclaration
  private static boolean ComponentCallbackPropertyDeclaration_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ComponentCallbackPropertyDeclaration_2_0")) return false;
    boolean result_;
    result_ = ComponentCallbackPropertyReturnDeclaration(builder_, level_ + 1);
    if (!result_) result_ = ComponentCallbackPropertyBindingDeclaration(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // LPAREN (FieldType ','?)* RPAREN
  static boolean ComponentCallbackPropertyParametersDeclaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ComponentCallbackPropertyParametersDeclaration")) return false;
    if (!nextTokenIs(builder_, LPAREN)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LPAREN);
    result_ = result_ && ComponentCallbackPropertyParametersDeclaration_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RPAREN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (FieldType ','?)*
  private static boolean ComponentCallbackPropertyParametersDeclaration_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ComponentCallbackPropertyParametersDeclaration_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!ComponentCallbackPropertyParametersDeclaration_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "ComponentCallbackPropertyParametersDeclaration_1", pos_)) break;
    }
    return true;
  }

  // FieldType ','?
  private static boolean ComponentCallbackPropertyParametersDeclaration_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ComponentCallbackPropertyParametersDeclaration_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = FieldType(builder_, level_ + 1);
    result_ = result_ && ComponentCallbackPropertyParametersDeclaration_1_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ','?
  private static boolean ComponentCallbackPropertyParametersDeclaration_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ComponentCallbackPropertyParametersDeclaration_1_0_1")) return false;
    consumeToken(builder_, COMMA);
    return true;
  }

  /* ********************************************************** */
  // ComponentCallbackPropertyParametersDeclaration ('->' FieldType)?
  static boolean ComponentCallbackPropertyReturnDeclaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ComponentCallbackPropertyReturnDeclaration")) return false;
    if (!nextTokenIs(builder_, LPAREN)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = ComponentCallbackPropertyParametersDeclaration(builder_, level_ + 1);
    result_ = result_ && ComponentCallbackPropertyReturnDeclaration_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ('->' FieldType)?
  private static boolean ComponentCallbackPropertyReturnDeclaration_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ComponentCallbackPropertyReturnDeclaration_1")) return false;
    ComponentCallbackPropertyReturnDeclaration_1_0(builder_, level_ + 1);
    return true;
  }

  // '->' FieldType
  private static boolean ComponentCallbackPropertyReturnDeclaration_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ComponentCallbackPropertyReturnDeclaration_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, "->");
    result_ = result_ && FieldType(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // EXPORT? COMPONENT? Identifier (INHERITS Identifier)? ComponentFieldDeclarationBlock
  public static boolean ComponentDeclaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ComponentDeclaration")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, COMPONENT_DECLARATION, "<component declaration>");
    result_ = ComponentDeclaration_0(builder_, level_ + 1);
    result_ = result_ && ComponentDeclaration_1(builder_, level_ + 1);
    result_ = result_ && Identifier(builder_, level_ + 1);
    result_ = result_ && ComponentDeclaration_3(builder_, level_ + 1);
    result_ = result_ && ComponentFieldDeclarationBlock(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // EXPORT?
  private static boolean ComponentDeclaration_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ComponentDeclaration_0")) return false;
    consumeToken(builder_, EXPORT);
    return true;
  }

  // COMPONENT?
  private static boolean ComponentDeclaration_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ComponentDeclaration_1")) return false;
    consumeToken(builder_, COMPONENT);
    return true;
  }

  // (INHERITS Identifier)?
  private static boolean ComponentDeclaration_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ComponentDeclaration_3")) return false;
    ComponentDeclaration_3_0(builder_, level_ + 1);
    return true;
  }

  // INHERITS Identifier
  private static boolean ComponentDeclaration_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ComponentDeclaration_3_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, INHERITS);
    result_ = result_ && Identifier(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // TYPE_LITERAL? ComponentDeclaration
  static boolean ComponentElementDeclaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ComponentElementDeclaration")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = ComponentElementDeclaration_0(builder_, level_ + 1);
    result_ = result_ && ComponentDeclaration(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // TYPE_LITERAL?
  private static boolean ComponentElementDeclaration_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ComponentElementDeclaration_0")) return false;
    consumeToken(builder_, TYPE_LITERAL);
    return true;
  }

  /* ********************************************************** */
  // LBRACE ComponentFieldDeclarationBlockItem* RBRACE
  public static boolean ComponentFieldDeclarationBlock(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ComponentFieldDeclarationBlock")) return false;
    if (!nextTokenIs(builder_, LBRACE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LBRACE);
    result_ = result_ && ComponentFieldDeclarationBlock_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RBRACE);
    exit_section_(builder_, marker_, COMPONENT_FIELD_DECLARATION_BLOCK, result_);
    return result_;
  }

  // ComponentFieldDeclarationBlockItem*
  private static boolean ComponentFieldDeclarationBlock_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ComponentFieldDeclarationBlock_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!ComponentFieldDeclarationBlockItem(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "ComponentFieldDeclarationBlock_1", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // ComponentPropertyDeclaration
  //     | ComponentCallbackPropertyDeclaration
  //     | ComponentCallbackDeclaration
  //     | ElementFieldDeclaration
  //     | ComponentElementDeclaration
  //     | ComponentAnimateDeclaration
  //     | ComponentStatesDeclaration
  static boolean ComponentFieldDeclarationBlockItem(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ComponentFieldDeclarationBlockItem")) return false;
    boolean result_;
    result_ = ComponentPropertyDeclaration(builder_, level_ + 1);
    if (!result_) result_ = ComponentCallbackPropertyDeclaration(builder_, level_ + 1);
    if (!result_) result_ = ComponentCallbackDeclaration(builder_, level_ + 1);
    if (!result_) result_ = ElementFieldDeclaration(builder_, level_ + 1);
    if (!result_) result_ = ComponentElementDeclaration(builder_, level_ + 1);
    if (!result_) result_ = ComponentAnimateDeclaration(builder_, level_ + 1);
    if (!result_) result_ = ComponentStatesDeclaration(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // PARAMETER_LITERAL
  static boolean ComponentFieldName(PsiBuilder builder_, int level_) {
    return consumeToken(builder_, PARAMETER_LITERAL);
  }

  /* ********************************************************** */
  // (PRIVATE | IN | OUT | IN_OUNT)? PROPERTY '<' Identifier  '>' PropertyName ';'
  static boolean ComponentPropertyDeclaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ComponentPropertyDeclaration")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = ComponentPropertyDeclaration_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, PROPERTY);
    result_ = result_ && consumeToken(builder_, "<");
    result_ = result_ && Identifier(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, ">");
    result_ = result_ && PropertyName(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, SEMICOLON);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (PRIVATE | IN | OUT | IN_OUNT)?
  private static boolean ComponentPropertyDeclaration_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ComponentPropertyDeclaration_0")) return false;
    ComponentPropertyDeclaration_0_0(builder_, level_ + 1);
    return true;
  }

  // PRIVATE | IN | OUT | IN_OUNT
  private static boolean ComponentPropertyDeclaration_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ComponentPropertyDeclaration_0_0")) return false;
    boolean result_;
    result_ = consumeToken(builder_, PRIVATE);
    if (!result_) result_ = consumeToken(builder_, IN);
    if (!result_) result_ = consumeToken(builder_, OUT);
    if (!result_) result_ = consumeToken(builder_, IN_OUNT);
    return result_;
  }

  /* ********************************************************** */
  // STATES LBRACKET
  static boolean ComponentStatesDeclaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ComponentStatesDeclaration")) return false;
    if (!nextTokenIs(builder_, STATES)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, STATES, LBRACKET);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // ImportDeclaration
  //     | StructDeclaration
  //     | ComponentDeclaration
  static boolean Declaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Declaration")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = ImportDeclaration(builder_, level_ + 1);
    if (!result_) result_ = StructDeclaration(builder_, level_ + 1);
    if (!result_) result_ = ComponentDeclaration(builder_, level_ + 1);
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
  // ComponentFieldName Expression SEMICOLON
  static boolean ElementFieldDeclaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ElementFieldDeclaration")) return false;
    if (!nextTokenIs(builder_, PARAMETER_LITERAL)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = ComponentFieldName(builder_, level_ + 1);
    result_ = result_ && Expression(builder_, level_ + 1, -1);
    result_ = result_ && consumeToken(builder_, SEMICOLON);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // 'int'
  //     | 'float'
  //     | 'bool'
  //     | 'string'
  //     | 'color'
  //     | 'brush'
  //     | 'physical-length'
  //     | 'length'
  //     | 'duration'
  //     | 'angle'
  //     | 'easing'
  //     | 'percent'
  //     | 'image'
  //     | 'relative-font-size'
  public static boolean FieldType(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FieldType")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, FIELD_TYPE, "<field type>");
    result_ = consumeToken(builder_, "int");
    if (!result_) result_ = consumeToken(builder_, "float");
    if (!result_) result_ = consumeToken(builder_, "bool");
    if (!result_) result_ = consumeToken(builder_, "string");
    if (!result_) result_ = consumeToken(builder_, "color");
    if (!result_) result_ = consumeToken(builder_, "brush");
    if (!result_) result_ = consumeToken(builder_, "physical-length");
    if (!result_) result_ = consumeToken(builder_, "length");
    if (!result_) result_ = consumeToken(builder_, "duration");
    if (!result_) result_ = consumeToken(builder_, "angle");
    if (!result_) result_ = consumeToken(builder_, "easing");
    if (!result_) result_ = consumeToken(builder_, "percent");
    if (!result_) result_ = consumeToken(builder_, "image");
    if (!result_) result_ = consumeToken(builder_, "relative-font-size");
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // IDENTIFIER
  static boolean Identifier(PsiBuilder builder_, int level_) {
    return consumeToken(builder_, IDENTIFIER);
  }

  /* ********************************************************** */
  // IMPORT ImportElementDeclarationBlock? ImportPathDeclaration ';'
  public static boolean ImportDeclaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ImportDeclaration")) return false;
    if (!nextTokenIs(builder_, IMPORT)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, IMPORT);
    result_ = result_ && ImportDeclaration_1(builder_, level_ + 1);
    result_ = result_ && ImportPathDeclaration(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, SEMICOLON);
    exit_section_(builder_, marker_, IMPORT_DECLARATION, result_);
    return result_;
  }

  // ImportElementDeclarationBlock?
  private static boolean ImportDeclaration_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ImportDeclaration_1")) return false;
    ImportElementDeclarationBlock(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // LBRACE (Identifier ','?)* RBRACE FROM
  public static boolean ImportElementDeclarationBlock(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ImportElementDeclarationBlock")) return false;
    if (!nextTokenIs(builder_, LBRACE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LBRACE);
    result_ = result_ && ImportElementDeclarationBlock_1(builder_, level_ + 1);
    result_ = result_ && consumeTokens(builder_, 0, RBRACE, FROM);
    exit_section_(builder_, marker_, IMPORT_ELEMENT_DECLARATION_BLOCK, result_);
    return result_;
  }

  // (Identifier ','?)*
  private static boolean ImportElementDeclarationBlock_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ImportElementDeclarationBlock_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!ImportElementDeclarationBlock_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "ImportElementDeclarationBlock_1", pos_)) break;
    }
    return true;
  }

  // Identifier ','?
  private static boolean ImportElementDeclarationBlock_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ImportElementDeclarationBlock_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = Identifier(builder_, level_ + 1);
    result_ = result_ && ImportElementDeclarationBlock_1_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ','?
  private static boolean ImportElementDeclarationBlock_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ImportElementDeclarationBlock_1_0_1")) return false;
    consumeToken(builder_, COMMA);
    return true;
  }

  /* ********************************************************** */
  // STRING_LITERAL
  public static boolean ImportPathDeclaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ImportPathDeclaration")) return false;
    if (!nextTokenIs(builder_, STRING_LITERAL)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, STRING_LITERAL);
    exit_section_(builder_, marker_, IMPORT_PATH_DECLARATION, result_);
    return result_;
  }

  /* ********************************************************** */
  // ADDITION
  //     | SUBTRACTION
  //     | MULTIPLY
  //     | DIVIDE
  //     | MODULO
  //     | FLOOR_DIVIDE
  public static boolean MathematicalOperators(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MathematicalOperators")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, MATHEMATICAL_OPERATORS, "<mathematical operators>");
    result_ = consumeToken(builder_, ADDITION);
    if (!result_) result_ = consumeToken(builder_, SUBTRACTION);
    if (!result_) result_ = consumeToken(builder_, MULTIPLY);
    if (!result_) result_ = consumeToken(builder_, DIVIDE);
    if (!result_) result_ = consumeToken(builder_, MODULO);
    if (!result_) result_ = consumeToken(builder_, FLOOR_DIVIDE);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // MathematicalOperators
  public static boolean Operators(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Operators")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, OPERATORS, "<operators>");
    result_ = MathematicalOperators(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // Identifier
  static boolean PropertyName(PsiBuilder builder_, int level_) {
    return Identifier(builder_, level_ + 1);
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
  // EXPORT? STRUCT Identifier StructFieldDeclarationBlock
  public static boolean StructDeclaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StructDeclaration")) return false;
    if (!nextTokenIs(builder_, "<struct declaration>", EXPORT, STRUCT)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, STRUCT_DECLARATION, "<struct declaration>");
    result_ = StructDeclaration_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, STRUCT);
    result_ = result_ && Identifier(builder_, level_ + 1);
    result_ = result_ && StructFieldDeclarationBlock(builder_, level_ + 1);
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
  // StructFieldName FieldType ','
  public static boolean StructFieldDeclaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StructFieldDeclaration")) return false;
    if (!nextTokenIs(builder_, PARAMETER_LITERAL)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = StructFieldName(builder_, level_ + 1);
    result_ = result_ && FieldType(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, COMMA);
    exit_section_(builder_, marker_, STRUCT_FIELD_DECLARATION, result_);
    return result_;
  }

  /* ********************************************************** */
  // LBRACE StructFieldDeclarationBlockItem* RBRACE
  public static boolean StructFieldDeclarationBlock(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StructFieldDeclarationBlock")) return false;
    if (!nextTokenIs(builder_, LBRACE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LBRACE);
    result_ = result_ && StructFieldDeclarationBlock_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RBRACE);
    exit_section_(builder_, marker_, STRUCT_FIELD_DECLARATION_BLOCK, result_);
    return result_;
  }

  // StructFieldDeclarationBlockItem*
  private static boolean StructFieldDeclarationBlock_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StructFieldDeclarationBlock_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!StructFieldDeclarationBlockItem(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "StructFieldDeclarationBlock_1", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // StructFieldDeclaration
  static boolean StructFieldDeclarationBlockItem(PsiBuilder builder_, int level_) {
    return StructFieldDeclaration(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // PARAMETER_LITERAL
  public static boolean StructFieldName(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StructFieldName")) return false;
    if (!nextTokenIs(builder_, PARAMETER_LITERAL)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, PARAMETER_LITERAL);
    exit_section_(builder_, marker_, STRUCT_FIELD_NAME, result_);
    return result_;
  }

  /* ********************************************************** */
  // EXPORT | IMPORT | STRUCT | COMPONENT
  static boolean TopLevelKeywords(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TopLevelKeywords")) return false;
    boolean result_;
    result_ = consumeToken(builder_, EXPORT);
    if (!result_) result_ = consumeToken(builder_, IMPORT);
    if (!result_) result_ = consumeToken(builder_, STRUCT);
    if (!result_) result_ = consumeToken(builder_, COMPONENT);
    return result_;
  }

  /* ********************************************************** */
  // EXCL
  //     | SUBTRACTION
  public static boolean UnaryOperators(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "UnaryOperators")) return false;
    if (!nextTokenIs(builder_, "<unary operators>", EXCL, SUBTRACTION)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, UNARY_OPERATORS, "<unary operators>");
    result_ = consumeToken(builder_, EXCL);
    if (!result_) result_ = consumeToken(builder_, SUBTRACTION);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // Expression root: Expression
  // Operator priority table:
  // 0: BINARY(TernaryExpression)
  // 1: BINARY(BinaryExpression)
  // 2: PREFIX(UnaryExpression)
  // 3: BINARY(AssignmentExpression)
  // 4: ATOM(PropertyExpression)
  // 5: ATOM(ColorExpression)
  // 6: ATOM(LengthExpression)
  // 7: ATOM(DurationExpression)
  // 8: ATOM(NumericExpression)
  // 9: ATOM(BooleanExpression)
  // 10: ATOM(StringExpression)
  // 11: ATOM(AlignmentExpression)
  // 12: BINARY(CallExpression)
  public static boolean Expression(PsiBuilder builder_, int level_, int priority_) {
    if (!recursion_guard_(builder_, level_, "Expression")) return false;
    addVariant(builder_, "<expression>");
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<expression>");
    result_ = UnaryExpression(builder_, level_ + 1);
    if (!result_) result_ = PropertyExpression(builder_, level_ + 1);
    if (!result_) result_ = ColorExpression(builder_, level_ + 1);
    if (!result_) result_ = LengthExpression(builder_, level_ + 1);
    if (!result_) result_ = DurationExpression(builder_, level_ + 1);
    if (!result_) result_ = NumericExpression(builder_, level_ + 1);
    if (!result_) result_ = BooleanExpression(builder_, level_ + 1);
    if (!result_) result_ = StringExpression(builder_, level_ + 1);
    if (!result_) result_ = AlignmentExpression(builder_, level_ + 1);
    pinned_ = result_;
    result_ = result_ && Expression_0(builder_, level_ + 1, priority_);
    exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
    return result_ || pinned_;
  }

  public static boolean Expression_0(PsiBuilder builder_, int level_, int priority_) {
    if (!recursion_guard_(builder_, level_, "Expression_0")) return false;
    boolean result_ = true;
    while (true) {
      Marker marker_ = enter_section_(builder_, level_, _LEFT_, null);
      if (priority_ < 0 && consumeTokenSmart(builder_, QUEST)) {
        result_ = report_error_(builder_, Expression(builder_, level_, 0));
        result_ = TernaryExpression_1(builder_, level_ + 1) && result_;
        exit_section_(builder_, level_, marker_, TERNARY_EXPRESSION, result_, true, null);
      }
      else if (priority_ < 1 && Operators(builder_, level_ + 1)) {
        result_ = Expression(builder_, level_, 1);
        exit_section_(builder_, level_, marker_, BINARY_EXPRESSION, result_, true, null);
      }
      else if (priority_ < 3 && leftMarkerIs(builder_, PROPERTY_EXPRESSION) && consumeTokenSmart(builder_, EQ)) {
        result_ = Expression(builder_, level_, 3);
        exit_section_(builder_, level_, marker_, ASSIGNMENT_EXPRESSION, result_, true, null);
      }
      else if (priority_ < 12 && leftMarkerIs(builder_, PROPERTY_EXPRESSION) && consumeTokenSmart(builder_, LPAREN)) {
        result_ = report_error_(builder_, Expression(builder_, level_, 12));
        result_ = consumeToken(builder_, RPAREN) && result_;
        exit_section_(builder_, level_, marker_, CALL_EXPRESSION, result_, true, null);
      }
      else {
        exit_section_(builder_, level_, marker_, null, false, false, null);
        break;
      }
    }
    return result_;
  }

  // ':' Expression
  private static boolean TernaryExpression_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TernaryExpression_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COLON);
    result_ = result_ && Expression(builder_, level_ + 1, -1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  public static boolean UnaryExpression(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "UnaryExpression")) return false;
    if (!nextTokenIsSmart(builder_, EXCL, SUBTRACTION)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = UnaryOperators(builder_, level_ + 1);
    pinned_ = result_;
    result_ = pinned_ && Expression(builder_, level_, 2);
    exit_section_(builder_, level_, marker_, UNARY_EXPRESSION, result_, pinned_, null);
    return result_ || pinned_;
  }

  // Identifier '.' Identifier
  public static boolean PropertyExpression(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PropertyExpression")) return false;
    if (!nextTokenIsSmart(builder_, IDENTIFIER)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = Identifier(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, DOT);
    result_ = result_ && Identifier(builder_, level_ + 1);
    exit_section_(builder_, marker_, PROPERTY_EXPRESSION, result_);
    return result_;
  }

  // COLOR_LITERAL | BasicColors
  public static boolean ColorExpression(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ColorExpression")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, COLOR_EXPRESSION, "<color expression>");
    result_ = consumeTokenSmart(builder_, COLOR_LITERAL);
    if (!result_) result_ = BasicColors(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // LENGTH_LITERAL
  public static boolean LengthExpression(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LengthExpression")) return false;
    if (!nextTokenIsSmart(builder_, LENGTH_LITERAL)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokenSmart(builder_, LENGTH_LITERAL);
    exit_section_(builder_, marker_, LENGTH_EXPRESSION, result_);
    return result_;
  }

  // DURATION_LITERAL
  public static boolean DurationExpression(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DurationExpression")) return false;
    if (!nextTokenIsSmart(builder_, DURATION_LITERAL)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokenSmart(builder_, DURATION_LITERAL);
    exit_section_(builder_, marker_, DURATION_EXPRESSION, result_);
    return result_;
  }

  // NUMERIC_LITERAL
  public static boolean NumericExpression(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "NumericExpression")) return false;
    if (!nextTokenIsSmart(builder_, NUMERIC_LITERAL)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokenSmart(builder_, NUMERIC_LITERAL);
    exit_section_(builder_, marker_, NUMERIC_EXPRESSION, result_);
    return result_;
  }

  // 'true' | 'false'
  public static boolean BooleanExpression(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "BooleanExpression")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, BOOLEAN_EXPRESSION, "<boolean expression>");
    result_ = consumeTokenSmart(builder_, "true");
    if (!result_) result_ = consumeTokenSmart(builder_, "false");
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // STRING_LITERAL
  public static boolean StringExpression(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StringExpression")) return false;
    if (!nextTokenIsSmart(builder_, STRING_LITERAL)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokenSmart(builder_, STRING_LITERAL);
    exit_section_(builder_, marker_, STRING_EXPRESSION, result_);
    return result_;
  }

  // 'stretch' | 'start' | 'center' | 'end' | 'space-between' | 'space-around'
  public static boolean AlignmentExpression(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AlignmentExpression")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ALIGNMENT_EXPRESSION, "<alignment expression>");
    result_ = consumeTokenSmart(builder_, "stretch");
    if (!result_) result_ = consumeTokenSmart(builder_, "start");
    if (!result_) result_ = consumeTokenSmart(builder_, "center");
    if (!result_) result_ = consumeTokenSmart(builder_, "end");
    if (!result_) result_ = consumeTokenSmart(builder_, "space-between");
    if (!result_) result_ = consumeTokenSmart(builder_, "space-around");
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

}
