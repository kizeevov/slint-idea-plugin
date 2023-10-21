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

public class SlintUnaryExpressionImpl extends SlintExpressionImpl implements SlintUnaryExpression {

  public SlintUnaryExpressionImpl(ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull SlintVisitor visitor) {
    visitor.visitUnaryExpression(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SlintVisitor) accept((SlintVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public SlintExpression getExpression() {
    return findChildByClass(SlintExpression.class);
  }

  @Override
  @NotNull
  public SlintUnaryOperators getUnaryOperators() {
    return findNotNullChildByClass(SlintUnaryOperators.class);
  }

}
