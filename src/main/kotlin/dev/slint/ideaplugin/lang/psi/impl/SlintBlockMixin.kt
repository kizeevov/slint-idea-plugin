package dev.slint.ideaplugin.lang.psi.impl

import com.intellij.lang.ASTNode
import com.intellij.psi.util.CachedValueProvider
import com.intellij.psi.util.CachedValuesManager
import com.intellij.psi.util.childrenOfType
import dev.slint.ideaplugin.lang.psi.SlintBlock
import dev.slint.ideaplugin.lang.psi.SlintElement
import dev.slint.ideaplugin.lang.psi.SlintNamedElement

abstract class SlintBlockMixin(node: ASTNode) : SlintElementImpl(node), SlintBlock {

    override fun getMembers(): List<SlintElement> = childrenOfType()

    override fun findMemberByName(name: String): SlintNamedElement? {
        return getMembersByName()[name]
    }

    override fun getMembersByName(): Map<String, SlintNamedElement> {
        return CachedValuesManager.getCachedValue(this) {
            val members = mutableMapOf<String, SlintNamedElement>()
            for (element in getMembers().filterIsInstance<SlintNamedElement>()) {
                val name = element.name
                if (!name.isNullOrEmpty()) {
                    members[name] = element
                }
            }
            CachedValueProvider.Result.create(members, this)
        }
    }
}