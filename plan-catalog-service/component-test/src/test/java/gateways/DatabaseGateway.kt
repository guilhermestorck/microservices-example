package gateways

import conf.Config
import conf.DatabaseTables
import java.sql.DriverManager
import java.util.Properties

object DatabaseGateway {

    private val connection by lazy {
        val url = Config.getEnv("JDBC_URL", "jdbc:postgresql://localhost:5432/plancatalogdb")
        val properties = Properties()
        properties["user"] = Config.getEnv("JDBC_USER", "postgres")
        properties["password"] = Config.getEnv("JDBC_PASSWORD", "root")
        properties["driver"] = Config.getEnv("JDBC_DRIVER", "org.postgresql.Driver")

        val connection = DriverManager.getConnection(url, properties)
        connection.autoCommit = true

        connection
    }

    fun cleanDatabase() {
        DatabaseTables.values().forEach { table ->
            connection.prepareStatement("DELETE FROM ${table.tableName}").execute()
        }
    }

    fun countRows(tableName: String): Int {
        val resultSet = connection.prepareStatement("SELECT COUNT(*) FROM $tableName").executeQuery()
        resultSet.next()
        return resultSet.getInt("count")
    }

    fun containsRow(tableName: String, values: Map<String, String>): Boolean {
        val whereClause = values.map { "\"${it.key}\" = '${it.value}'" }.joinToString(" AND ")
        val sql = "SELECT COUNT(*) FROM $tableName WHERE $whereClause"

        val resultSet = connection.prepareStatement(sql).executeQuery()
        resultSet.next()
        return resultSet.getInt("count") > 0
    }

    fun insertRow(tableName: String, row: Map<String, String>) {
        val orderedRow = row.toList()
        val columns = orderedRow.map { it.first }.joinToString(", ")
        val values = orderedRow.map { "'${it.second}'" }.joinToString(", ")

        val sql = "INSERT INTO $tableName($columns) VALUES ($values)"
        connection.prepareStatement(sql).execute()
    }
}