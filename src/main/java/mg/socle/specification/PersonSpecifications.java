/*
 * Copyright 2008-2011 the original author or authors. 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 */
package mg.socle.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import mg.socle.domain.Person;

/**
 * Collection of {@link Specification}s for a {@link Person}.
 * 
 * @author Oliver Gierke
 */
public class PersonSpecifications {

	/**
	 * A {@link Specification} to match on a {@link Person}'s firstName.
	 * 
	 * @param firstName
	 * @return
	 */
	public static Specification<Person> PersonHasFirstname(final String firstName) {

		return simplePropertySpec("firstName", firstName);
	}

	/**
	 * A {@link Specification} to match on a {@link Person}'s lastname.
	 * 
	 * @param firstName
	 * @return
	 */
	public static Specification<Person> PersonHasLastname(final String lastname) {

		return simplePropertySpec("lastname", lastname);
	}

	/**
	 * A {@link Specification} to do a like-match on a {@link Person}'s
	 * firstName.
	 * 
	 * @param firstName
	 * @return
	 */
	public static Specification<Person> PersonHasFirstnameLike(final String expression) {

		return new Specification<Person>() {

			public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				return cb.like(root.get("firstName").as(String.class), String.format("%%%s%%", expression));
			}
		};
	}

	private static <T> Specification<T> simplePropertySpec(final String property, final Object value) {

		return new Specification<T>() {

			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

				return builder.equal(root.get(property), value);
			}
		};
	}
}