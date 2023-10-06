package dev.slint.ideaplugin.lang.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.ResolveState
import com.intellij.psi.scope.PsiScopeProcessor
import dev.slint.ideaplugin.lang.SlintFileType
import dev.slint.ideaplugin.lang.SlintLanguage

class SlintFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, SlintLanguage) {

    override fun getFileType(): FileType = SlintFileType
//
//    val datasource: SlintDatasourceDeclaration?
//        get() = CachedValuesManager.getCachedValue(this) {
//            val declaration = declarations.find { it is SlintDatasourceDeclaration } as? SlintDatasourceDeclaration
//            CachedValueProvider.Result.create(declaration, this)
//        }
//
//    val datasourceType: SlintDatasourceType?
//        get() = CachedValuesManager.getCachedValue(this) {
//            val provider = datasource?.findMemberByName(SlintConstants.DatasourceFields.PROVIDER) as? SlintKeyValue
//            val providerValue = (provider?.expression as? SlintLiteralExpression)?.value as? String
//            val type = SlintDatasourceType.fromString(providerValue)
//            CachedValueProvider.Result.create(type, this)
//        }
//
//    val datasourceName: String?
//        get() = CachedValuesManager.getCachedValue(this) {
//            CachedValueProvider.Result.create(datasource?.name, this)
//        }
//
//    val declarations: List<SlintDeclaration>
//        get() = childrenOfType()
//
//    private val entityDeclarations: List<SlintEntityDeclaration>
//        get() = childrenOfType()

    override fun processDeclarations(
            processor: PsiScopeProcessor,
            state: ResolveState,
            lastParent: PsiElement?,
            place: PsiElement
    ): Boolean {
//        for (declaration in entityDeclarations) {
//            if (!processor.execute(declaration, state)) return false
//        }

        return super.processDeclarations(processor, state, lastParent, place)
    }
}