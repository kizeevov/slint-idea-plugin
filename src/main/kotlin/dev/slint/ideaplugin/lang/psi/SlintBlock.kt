package dev.slint.ideaplugin.lang.psi

interface SlintBlock : SlintElement {
    fun getMembers(): List<SlintElement>

    fun findMemberByName(name: String): SlintNamedElement?

    fun getMembersByName(): Map<String, SlintNamedElement>
}