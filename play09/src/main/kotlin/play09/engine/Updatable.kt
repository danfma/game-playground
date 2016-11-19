package play09.engine

interface Updatable {
    /**
     * Atualiza esta instância dado o tempo passado
     * desde o início da renderização.
     */
    fun update(time: TimeElapsed)
}

