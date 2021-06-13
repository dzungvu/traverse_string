package com.dzungvu.restring.core

interface RestringAppApi {
    /**
     * Load resource from internal storage
     * @param locale: Language id
     * @return Map<LocaleId: String, Map<stringId: String, stringValue: String>>
     */
    fun loadResource(locale: String?): Map<String, Map<String, String>>

    /**
     * Save resource with json string
     * Json string MUST follow:
     *
     * {
     *      "version":"1.0.0",
     *      "strings": [
     *          {
     *              "id":"text_open_app",
     *              "values": {
     *                  "vi":"Dang mo",
     *                  "en":"Opening"
     *              }
     *
     *          },
     *          {
     *              "id":"text_close_app",
     *              "values": {
     *                  "vi":"Thoat",
     *                  "en":"Exit"
     *              }
     *
     *          },
     *          ...
     *      ]
     * }
     *
     * @param json: Json input data
     */
    fun saveResource(json: String)

    /**
     * Save string resource with specific locale
     * @param locale: Language id
     * @param resource: Map<stringId: String, stringValue: String>
     */
    fun saveResource(locale: String, resource: Map<String, String>)

    /**
     * Inject data map to Restring app
     * @param data: Map<LocaleId: String, Map<stringId: String, stringValue: String>>
     */
    fun injectData(data: Map<String, Map<String, String>>)

    /**
     * Hardcode language for app
     */
    fun setLanguage(locale: String)

    /**
     * Return current app language
     */
    fun getLanguage(): String
}