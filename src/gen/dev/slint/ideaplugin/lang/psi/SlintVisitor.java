// This is a generated file. Not intended for manual editing.
package dev.slint.ideaplugin.lang.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;

public class SlintVisitor extends PsiElementVisitor {

  public void visitAlignmentExpression(@NotNull SlintAlignmentExpression o) {
    visitExpression(o);
  }

  public void visitAssignmentExpression(@NotNull SlintAssignmentExpression o) {
    visitExpression(o);
  }

  public void visitBinaryExpression(@NotNull SlintBinaryExpression o) {
    visitExpression(o);
  }

  public void visitBooleanExpression(@NotNull SlintBooleanExpression o) {
    visitExpression(o);
  }

  public void visitCallExpression(@NotNull SlintCallExpression o) {
    visitExpression(o);
  }

  public void visitColorExpression(@NotNull SlintColorExpression o) {
    visitExpression(o);
  }

  public void visitComponentDeclaration(@NotNull SlintComponentDeclaration o) {
    visitElement(o);
  }

  public void visitComponentFieldDeclarationBlock(@NotNull SlintComponentFieldDeclarationBlock o) {
    visitElement(o);
  }

  public void visitDurationExpression(@NotNull SlintDurationExpression o) {
    visitExpression(o);
  }

  public void visitExpression(@NotNull SlintExpression o) {
    visitElement(o);
  }

  public void visitFieldType(@NotNull SlintFieldType o) {
    visitElement(o);
  }

  public void visitImportDeclaration(@NotNull SlintImportDeclaration o) {
    visitElement(o);
  }

  public void visitImportElementDeclarationBlock(@NotNull SlintImportElementDeclarationBlock o) {
    visitElement(o);
  }

  public void visitImportPathDeclaration(@NotNull SlintImportPathDeclaration o) {
    visitElement(o);
  }

  public void visitLengthExpression(@NotNull SlintLengthExpression o) {
    visitExpression(o);
  }

  public void visitMathematicalOperators(@NotNull SlintMathematicalOperators o) {
    visitElement(o);
  }

  public void visitNumericExpression(@NotNull SlintNumericExpression o) {
    visitExpression(o);
  }

  public void visitOperators(@NotNull SlintOperators o) {
    visitElement(o);
  }

  public void visitPropertyExpression(@NotNull SlintPropertyExpression o) {
    visitExpression(o);
  }

  public void visitStringExpression(@NotNull SlintStringExpression o) {
    visitExpression(o);
  }

  public void visitStructDeclaration(@NotNull SlintStructDeclaration o) {
    visitElement(o);
  }

  public void visitStructFieldDeclaration(@NotNull SlintStructFieldDeclaration o) {
    visitElement(o);
  }

  public void visitStructFieldDeclarationBlock(@NotNull SlintStructFieldDeclarationBlock o) {
    visitElement(o);
  }

  public void visitStructFieldName(@NotNull SlintStructFieldName o) {
    visitElement(o);
  }

  public void visitTernaryExpression(@NotNull SlintTernaryExpression o) {
    visitExpression(o);
  }

  public void visitUnaryExpression(@NotNull SlintUnaryExpression o) {
    visitExpression(o);
  }

  public void visitUnaryOperators(@NotNull SlintUnaryOperators o) {
    visitElement(o);
  }

  public void visitElement(@NotNull SlintElement o) {
    super.visitElement(o);
  }

}
