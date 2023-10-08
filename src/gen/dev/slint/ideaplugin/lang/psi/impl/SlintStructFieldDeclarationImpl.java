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

public class SlintStructFieldDeclarationImpl extends SlintElementImpl implements SlintStructFieldDeclaration {

  public SlintStructFieldDeclarationImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull SlintVisitor visitor) {
    visitor.visitStructFieldDeclaration(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SlintVisitor) accept((SlintVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public SlintFieldType getFieldType() {
    return findChildByClass(SlintFieldType.class);
  }

  @Override
  @NotNull
  public PsiElement getParameterLiteral() {
    return findNotNullChildByType(PARAMETER_LITERAL);
  }

}
