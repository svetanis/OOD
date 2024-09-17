package com.svetanis.ood.onlinereader.model;

import static com.google.common.base.MoreObjects.toStringHelper;
import static com.google.common.base.Optional.absent;
import static com.google.common.collect.Ordering.natural;
import static com.svetanis.java.base.Objects.equalByComparison;
import static com.svetanis.java.base.validate.Validation.checkNoBlanks;
import static java.util.Objects.hash;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects.ToStringHelper;
import com.google.common.base.Optional;
import com.google.common.collect.ComparisonChain;
import com.svetanis.java.base.serialize.jaxb.BuildingAdapter;
import com.svetanis.java.base.validate.IgnoreValidation;

@JsonDeserialize(builder = Address.Builder.class)
public final class Address implements Comparable<Address> {

	private final String street;
	private final String city;
	private final Optional<String> state;
	private final String postalCode;
	private final String country;
	@IgnoreValidation
	private final int hash;

	private Address(Builder builder) {
		this.street = builder.street;
		this.city = builder.city;
		this.state = builder.state;
		this.postalCode = builder.postalCode;
		this.country = builder.country;
		this.hash = hash(street, city, state, postalCode, country);
	}

	public static final Builder builder() {
		return new Builder();
	}

	public static class Builder extends BuildingAdapter<Builder, Address> {

		private String street;
		private String city;
		private Optional<String> state = absent();
		private String postalCode;
		private String country;

		public final Builder withStreet(String street) {
			this.street = street;
			return this;
		}

		public final Builder withCity(String city) {
			this.city = city;
			return this;
		}

		public final Builder withState(Optional<String> state) {
			this.state = state;
			return this;
		}

		public final Builder withPostalCode(String postalCode) {
			this.postalCode = postalCode;
			return this;
		}

		public final Builder withCountry(String country) {
			this.country = country;
			return this;
		}

		@Override
		public Address build() {
			return validate(new Address(this));
		}

		@Override
		public Address get() {
			return build();
		}

		private static Address validate(Address instance) {
			checkNoBlanks(instance);
			return instance;
		}

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
		helper.add("street", street);
		helper.add("city", city);
		helper.add("state", state);
		helper.add("postalCode", postalCode);
		helper.add("country", country);
		return helper.toString();
	}

	@Override
	public int compareTo(Address other) {
		ComparisonChain chain = ComparisonChain.start();
		chain = chain.compare(street, other.street);
		chain = chain.compare(city, other.city);
		chain = chain.compare(state.orNull(), other.state.orNull(), natural().nullsFirst());
		chain = chain.compare(postalCode, other.postalCode);
		return chain.result();
	}

}