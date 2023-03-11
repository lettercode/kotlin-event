package ch.lettercode.kotlin.event

public class EventHandler<T>(private val handler: (T) -> Unit) {
    public operator fun invoke(eventArgs: T) {
        this.handler(eventArgs)
    }
}

public class Event<T> : MutableList<EventHandler<T>> by mutableListOf() {
    public inline operator fun plusAssign(crossinline handler: (T) -> Unit): Unit =
        plusAssign(EventHandler { handler(it) })

    public operator fun invoke(eventArgs: T) {
        for (handler in this) handler(eventArgs)
    }
}
