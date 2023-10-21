// This is a generated file. Not intended for manual editing.
package dev.slint.ideaplugin.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface SlintComponentFieldDeclarationBlock extends SlintElement {

  @NotNull
  List<SlintComponentDeclaration> getComponentDeclarationList();

  @NotNull
  List<SlintExpression> getExpressionList();

  @NotNull
  List<SlintFieldType> getFieldTypeList();

}
