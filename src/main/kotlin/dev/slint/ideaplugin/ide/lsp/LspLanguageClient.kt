package dev.slint.ideaplugin.ide.lsp

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.annotations.JsonAdapter
import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project
import com.intellij.platform.lsp.api.Lsp4jClient
import com.intellij.platform.lsp.api.LspServerNotificationsHandler
import dev.slint.ideaplugin.SlintBundle
import org.eclipse.lsp4j.jsonrpc.services.JsonNotification
import java.lang.reflect.Type

class LspLanguageClient(
    serverNotificationsHandler: LspServerNotificationsHandler,
    private val project: Project
) : Lsp4jClient(serverNotificationsHandler)
{
    // when editing, it flaps with notifications
//    @JsonNotification("experimental/serverStatus")
//    fun serverStatus(status: ServerStatus) {
//        when (status.health) {
//            Health.WARNING -> {
//                NotificationGroupManager.getInstance()
//                    .getNotificationGroup("Slint")
//                    .createNotification(
//                        SlintBundle.message("slint.language.server.status"),
//                        status.message,
//                        NotificationType.WARNING
//                    )
//                    .notify(project)
//            }
//            Health.ERROR -> {
//                NotificationGroupManager.getInstance()
//                    .getNotificationGroup("Slint")
//                    .createNotification(
//                        SlintBundle.message("slint.language.server.status"),
//                        status.message,
//                        NotificationType.ERROR
//                    )
//                    .notify(project)
//            }
//
//            Health.OK -> {}
//        }
//    }

    data class ServerStatus(
        @JsonAdapter(EnumDeserializer::class)
        val health: Health,
        val message: String,
        val quiescent: Boolean,
    )

    enum class Health {
        OK,
        WARNING,
        ERROR;
    }

    class EnumDeserializer<T : Enum<T>>() : JsonDeserializer<T> {
        override fun deserialize(json: JsonElement?, typeOfT: Type?,
                                 context: JsonDeserializationContext?): T? {
            return json?.asString?.let {
                if (it.isNotEmpty()) {
                    val enumClass = typeOfT as? Class<T>
                    return enumClass?.enumConstants?.first {
                            enumValue -> enumValue.name.equals(it, true) }
                } else {
                    return null
                }
            }
        }
    }
}