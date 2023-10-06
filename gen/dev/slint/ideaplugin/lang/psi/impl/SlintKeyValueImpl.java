// This is a generated file. Not intended for manual editing.
package dev.slint.ideaplugin.lang.psi.impl;

import dev.slint.ideaplugin.lang.psi.SlintKeyValue;
import dev.slint.ideaplugin.lang.psi.SlintElementTypes;
import dev.slint.ideaplugin.lang.psi.SlintVisitor;
import dev.slint.ideaplugin.lang.psi.impl.SlintElementImpl;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import dev.slint.ideaplugin.lang.psi.*;

public class SlintKeyValueImpl extends SlintElementImpl implements SlintKeyValue {

  public SlintKeyValueImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull SlintVisitor visitor) {
    visitor.visitKeyValue(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SlintVisitor) accept((SlintVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public PsiElement getIdentifier() {
    return findNotNullChildByType(SlintElementTypes.IDENTIFIER);
  }

}
