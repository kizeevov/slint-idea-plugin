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

public class SlintFieldTypeImpl extends SlintElementImpl implements SlintFieldType {

  public SlintFieldTypeImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull SlintVisitor visitor) {
    visitor.visitFieldType(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SlintVisitor) accept((SlintVisitor)visitor);
    else super.accept(visitor);
  }

}
