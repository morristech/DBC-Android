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

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Trevor
 */
@RunWith(JUnit4.class)
public class MapConditionTest
{
	Map<String, String> input;

	@Before
	public void setUp() throws Exception
	{
		this.input = new LinkedHashMap<>();
		this.input.put("KeyOne", "ValOne");
		this.input.put("KeyTwo", "ValTwo");
		this.input.put("KeyThree", "ValThree");
	}

	@Test
	public void isNotEmpty() throws Exception
	{
		MapCondition<Map<String, String>, String, String> condition = new MapCondition<>(this.input);
		condition.isNotEmpty();

		MapCondition<Map<String, String>, String, String> badCondition = new MapCondition<>(new LinkedHashMap<>());
		Assertions.assertThatThrownBy(badCondition::isNotEmpty)
		          .isInstanceOf(DbcAssertionError.class)
		          .hasCauseInstanceOf(IllegalArgumentException.class);
	}

	@Test
	public void hasSize() throws Exception
	{
		MapCondition<Map<String, String>, String, String> condition = new MapCondition<>(this.input);
		condition.hasSize(3);

		Assertions.assertThatThrownBy(() -> condition.hasSize(2))
		          .isInstanceOf(DbcAssertionError.class)
		          .hasCauseInstanceOf(IllegalArgumentException.class);
	}

	@Test
	public void hasSizeBetween() throws Exception
	{
		MapCondition<Map<String, String>, String, String> condition = new MapCondition<>(this.input);
		condition.hasSizeBetween(3, 5);

		Assertions.assertThatThrownBy(() -> condition.hasSizeBetween(25, 624))
		          .isInstanceOf(DbcAssertionError.class)
		          .hasCauseInstanceOf(IllegalArgumentException.class);
	}

	@Test
	public void containsKey() throws Exception
	{
		MapCondition<Map<String, String>, String, String> condition = new MapCondition<>(this.input);
		condition.containsKey("KeyTwo");

		Assertions.assertThatThrownBy(() -> condition.containsKey("Chicago"))
		          .isInstanceOf(DbcAssertionError.class)
		          .hasCauseInstanceOf(IllegalArgumentException.class);
	}

	@Test
	public void containsValue() throws Exception
	{
		MapCondition<Map<String, String>, String, String> condition = new MapCondition<>(this.input);
		condition.containsValue("ValOne");

		Assertions.assertThatThrownBy(() -> condition.containsValue("Chicago"))
		          .isInstanceOf(DbcAssertionError.class)
		          .hasCauseInstanceOf(IllegalArgumentException.class);

	}

	@Test
	public void hasSizeGreaterThan() throws Exception
	{
		MapCondition<Map<String, String>, String, String> condition = new MapCondition<>(this.input);
		condition.hasSizeGreaterThan(2);

		Assertions.assertThatThrownBy(() -> condition.hasSizeGreaterThan(3))
		          .isInstanceOf(DbcAssertionError.class)
		          .hasCauseInstanceOf(IllegalStateException.class);
	}

	@Test
	public void hasSizeGreaterThanOrEqual() throws Exception
	{
		MapCondition<Map<String, String>, String, String> condition = new MapCondition<>(this.input);
		condition.hasSizeGreaterThanOrEqual(3);

		Assertions.assertThatThrownBy(() -> condition.hasSizeGreaterThanOrEqual(4))
		          .isInstanceOf(DbcAssertionError.class)
		          .hasCauseInstanceOf(IllegalStateException.class);
	}

	@Test
	public void hasSizeLessThan() throws Exception
	{
		MapCondition<Map<String, String>, String, String> condition = new MapCondition<>(this.input);
		condition.hasSizeLessThan(4);

		Assertions.assertThatThrownBy(() -> condition.hasSizeLessThan(2))
		          .isInstanceOf(DbcAssertionError.class)
		          .hasCauseInstanceOf(IllegalStateException.class);

	}

	@Test
	public void hasSizeLessThanOrEqual() throws Exception
	{
		MapCondition<Map<String, String>, String, String> condition = new MapCondition<>(this.input);
		condition.hasSizeLessThanOrEqual(3);

		Assertions.assertThatThrownBy(() -> condition.hasSizeLessThanOrEqual(2))
		          .isInstanceOf(DbcAssertionError.class)
		          .hasCauseInstanceOf(IllegalStateException.class);
	}
}