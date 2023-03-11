package ch.lettercode.kotlin.event

import kotlin.test.Test
import kotlin.test.assertEquals

class EventTest {
    @Test
    fun size() {
        val event = Event<Any>()

        event += EventHandler { }
        event += EventHandler { }
        event += EventHandler { }

        assertEquals(3, event.size)
    }

    @Test
    fun clear() {
        val event = Event<Any>()

        event += EventHandler { }
        event += EventHandler { }

        event.clear()

        assertEquals(0, event.size)
    }

    @Test
    fun iterator() {
        val event = Event<Any>()

        event += EventHandler { }
        event += EventHandler { }
        event += EventHandler { }

        var count = 0
        for (handler in event)
            count++

        assertEquals(3, count)
    }

    @Test
    fun addEventHandler() {
        val event = Event<Any>()

        event += EventHandler { }

        event(Any())

        assertEquals(1, event.size)
    }

    @Test
    fun addLambdaEventHandler() {
        val event = Event<Any>()

        event += {}

        event(Any())

        assertEquals(1, event.size)
    }

    @Test
    fun remove() {
        val event = Event<Any>()

        val eventHandler1 = EventHandler<Any> { }
        val eventHandler2 = EventHandler<Any> { }

        event += eventHandler1
        event += eventHandler2

        event -= eventHandler1

        event(Any())

        assertEquals(1, event.size)
    }

    @Test
    fun sumValues() {
        data class EventArgs(var value: Int)

        val event = Event<EventArgs>()
        var testee = 0

        event += EventHandler {
            testee += it.value
        }

        event(EventArgs(1))
        event(EventArgs(1))

        assertEquals(2, testee)
    }

    @Test
    fun plusAssign_CrossInline() {
        data class EventArgs(var value: Int)

        val event = Event<EventArgs>()
        var testee = 0

        event += {
            testee += it.value
        }

        event(EventArgs(1))
        event(EventArgs(1))

        assertEquals(2, testee)
    }
}
