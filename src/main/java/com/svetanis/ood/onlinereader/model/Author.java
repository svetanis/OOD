package com.svetanis.ood.onlinereader.model;

import static com.google.common.base.MoreObjects.toStringHelper;
import static com.google.common.base.Optional.absent;
import static com.google.common.collect.Ordering.natural;
import static com.svetanis.java.base.Objects.equalByComparison;
import static com.svetanis.java.base.Optionals.lcase;
import static com.svetanis.java.base.Strings.lcase;
import static com.svetanis.java.base.validate.Validation.checkNoBlanks;
import static java.util.Objects.hash;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects.ToStringHelper;
import com.google.common.base.Optional;
import com.google.common.collect.ComparisonChain;
import com.svetanis.java.base.serialize.jaxb.BuildingAdapter;
import com.svetanis.java.base.validate.IgnoreValidation;

@JsonDeserialize(builder = Author.Builder.class)
public final class Author implements Comparable<Author> {

	private final String name;
	private final Optional<String> description;
	@IgnoreValidation
	private final int hash;

	private Author(Builder builder) {
		this.name = lcase(builder.name);
		this.description = lcase(builder.description);
		this.hash = hash(description, description);
	}

	public static final Author build(String name) {
		return build(name, absent());
	}

	public static final Author build(String name, Optional<String> description) {
		Builder builder = builder();
		builder.withName(name);
		builder.withDescription(description);
		return builder.build();
	}

	public static final Builder builder() {
		return new Builder();
	}

	public static class Builder extends BuildingAdapter<Builder, Author> {

		private String name;
		private Optional<String> description = absent();

		public final Builder withName(String name) {
			this.name = name;
			return this;
		}

		public final Builder withDescription(Optional<String> description) {
			this.description = description;
			return this;
		}

		@Override
		public Author build() {
			return validate(new Author(this));
		}

		@Override
		public Author get() {
			return build();
		}

		private static Author validate(Author instance) {
			checkNoBlanks(instance);
			return instance;
		}

	}

	public String getName() {
		return name;
	}

	public Optional<String> getDescription() {
		return description;
	}

	@Override
	public int hashCode() {
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		return equalByComparison(this, object, true);
	}

	@Override
	public String toString() {
		ToStringHelper helper = toStringHelper(this);
		helper.add("name", name);
		helper.add("description", description);
		return helper.toString();
	}

	@Override
	public int compareTo(Author other) {
		ComparisonChain chain = ComparisonChain.start();
		chain = chain.compare(name, other.name);
		chain = chain.compare(description.orNull(), other.description.orNull(), natural().nullsFirst());
		return chain.result();
	}

}