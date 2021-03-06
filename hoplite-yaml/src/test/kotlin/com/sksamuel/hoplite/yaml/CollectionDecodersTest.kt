package com.sksamuel.hoplite.yaml

import arrow.core.NonEmptyList
import com.sksamuel.hoplite.ConfigLoader
import io.kotlintest.shouldBe
import io.kotlintest.specs.FunSpec

class CollectionDecodersTest : FunSpec() {
  init {
    test("List<T> as delimited string") {
      data class Test(val strings: List<String>, val longs: List<Long>)

      val config = ConfigLoader().loadConfigOrThrow<Test>("/test_array_as_delimited_string.yml")
      config shouldBe Test(listOf("1", "2", "a", "b"), listOf(1, 2, 3, 4))
    }

    test("List<T>") {
      data class Test(val strings: List<String>, val longs: List<Long>)

      val config = ConfigLoader().loadConfigOrThrow<Test>("/test_array.yml")
      config shouldBe Test(listOf("1", "2", "a", "b"), listOf(1, 2, 3, 4))
    }

    test("Set<T>") {
      data class Test(val strings: Set<String>, val longs: Set<Long>)

      val config = ConfigLoader().loadConfigOrThrow<Test>("/test_set.yml")
      config shouldBe Test(setOf("1", "2", "a", "b"), setOf(1, 2, 3, 4))
    }

    test("nullable Set<T> with values") {
      data class Test(val strings: Set<String>?, val longs: Set<Long>?)

      val config = ConfigLoader().loadConfigOrThrow<Test>("/test_set.yml")
      config shouldBe Test(setOf("1", "2", "a", "b"), setOf(1, 2, 3, 4))
    }

    test("nullable Set<T> without values") {
      data class Test(val strings: Set<String>?, val longs: Set<Long>?)

      val config = ConfigLoader().loadConfigOrThrow<Test>("/basic.yml")
      config shouldBe Test(null, null)
    }

    test("NonEmptyList<A> as delimited string") {
      data class Test(val strings: NonEmptyList<String>, val longs: NonEmptyList<Long>)

      val config = ConfigLoader().loadConfigOrThrow<Test>("/test_array_as_delimited_string.yml")
      config shouldBe Test(NonEmptyList.of("1", "2", "a", "b"), NonEmptyList.of(1, 2, 3, 4))
    }

    test("nullable List<T> with values") {
      data class Test(val strings: List<String>?, val longs: List<Long>?)

      val config = ConfigLoader().loadConfigOrThrow<Test>("/test_array.yml")
      config shouldBe Test(listOf("1", "2", "a", "b"), listOf(1, 2, 3, 4))
    }

    test("nullable List<T> without values") {
      data class Test(val strings: List<String>?, val longs: List<Long>?)

      val config = ConfigLoader().loadConfigOrThrow<Test>("/basic.yml")
      config shouldBe Test(null, null)
    }
  }
}
