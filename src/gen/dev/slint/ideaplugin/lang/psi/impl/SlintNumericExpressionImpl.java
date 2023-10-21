// This is a generated file. Not intended for manual editing.
package dev.slint.ideaplugin.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static dev.slint.ideaplugin.lang.psi.SlintElementTypes.*;
import dev.slint.ideaplugin.lang.psi.*;

public class SlintNumericExpressionImpl extends SlintExpressionImpl implements SlintNumericExpression {

  public SlintNumericExpressionImpl(ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull SlintVisitor visitor) {
    visitor.visitNumericExpression(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SlintVisitor) accept((SlintVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public PsiElement getNumericLiteral() {
    return findNotNullChildByType(NUMERIC_LITERAL);
  }

}
