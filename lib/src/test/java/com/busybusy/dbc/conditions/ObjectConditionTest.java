/*
 * Copyright 2016 Busy, LLC
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.busybusy.dbc.conditions;

import com.busybusy.dbc.DbcAssertionError;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * @author Trevor
 */
@RunWith(JUnit4.class)
public class ObjectConditionTest
{
	@Test
	public void isNull() throws Exception
	{
		ObjectCondition<Object> nullCondition = new ObjectCondition<>(null);
		nullCondition.isNull();

		ObjectCondition<Object> nonNullCondition = new ObjectCondition<>(new Object());

		assertThatThrownBy(nonNullCondition::isNull)
				.isInstanceOf(DbcAssertionError.class)
				.hasCauseInstanceOf(IllegalArgumentException.class);
	}

	@Test
	public void isNotNull() throws Exception
	{
		ObjectCondition<Object> nonNullCondition = new ObjectCondition<>(new Object());
		nonNullCondition.isNotNull();

		ObjectCondition<Object> nullCondition = new ObjectCondition<>(null);

		assertThatThrownBy(nullCondition::isNotNull)
				.isInstanceOf(DbcAssertionError.class)
				.hasCauseInstanceOf(NullPointerException.class);
	}

	@Test
	public void passes() throws Exception
	{
		ObjectCondition<Object> condition = new ObjectCondition<>(new Object());
		condition.passes(subject -> true);

		assertThatThrownBy(() -> condition.passes(subject -> false))
				.isInstanceOf(DbcAssertionError.class)
				.hasCauseInstanceOf(IllegalArgumentException.class);
	}

	@Test
	public void fails() throws Exception
	{
		ObjectCondition<Object> condition = new ObjectCondition<>(new Object());
		condition.fails(subject -> false);

		assertThatThrownBy(() -> condition.fails(subject -> true))
				.isInstanceOf(DbcAssertionError.class)
				.hasCauseInstanceOf(IllegalArgumentException.class);
	}

	@Test
	public void isEqualTo() throws Exception
	{
		ObjectCondition<Integer> condition = new ObjectCondition<>(2)
				.message("Checking that the generics signatures are correct")
				.passes(subject -> true)
				.isEqualTo(2);

		assertThatThrownBy(() -> condition.isEqualTo(3))
				.isInstanceOf(DbcAssertionError.class)
				.hasCauseInstanceOf(IllegalArgumentException.class);
	}

	@Test
	public void isEqualToCustomComparator() throws Exception
	{
		ObjectCondition<Integer> condition = new ObjectCondition<>(2);
		condition.isEqualTo(2, (integer, t1) -> integer - t1);

		assertThatThrownBy(() -> condition.isEqualTo(2, (integer, t1) -> 1))
				.isInstanceOf(DbcAssertionError.class)
				.hasCauseInstanceOf(IllegalArgumentException.class);
	}

	@Test
	public void isNotEqualTo() throws Exception
	{
		ObjectCondition<Integer> condition = new ObjectCondition<>(2);
		condition.isNotEqualTo(3);

		assertThatThrownBy(() -> condition.isNotEqualTo(2))
				.isInstanceOf(DbcAssertionError.class)
				.hasCauseInstanceOf(IllegalArgumentException.class);
	}

	@Test
	public void isNotEqualToCustomComparator() throws Exception
	{
		ObjectCondition<Integer> condition = new ObjectCondition<>(2);
		condition.isNotEqualTo(3, (integer, t1) -> integer - t1);

		assertThatThrownBy(() -> condition.isNotEqualTo(2, (integer, t1) -> 0))
				.isInstanceOf(DbcAssertionError.class)
				.hasCauseInstanceOf(IllegalArgumentException.class);
	}
}