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

@JsonDeserialize(builder = User.Builder.class)
public final class User implements Comparable<User> {

	private final String name;
	private final String email;
	private final Optional<String> phone;
	private final Optional<Address> address;
	@IgnoreValidation
	private final int hash;

	private User(Builder builder) {
		this.name = builder.name;
		this.email = builder.email;
		this.phone = builder.phone;
		this.address = builder.address;
		this.hash = hash(name, email, phone, address);
	}

	public static final Builder builder() {
		return new Builder();
	}

	public static class Builder extends BuildingAdapter<Builder, User> {

		private String name;
		private String email;
		private Optional<String> phone = absent();
		private Optional<Address> address = absent();

		public final Builder withName(String name) {
			this.name = name;
			return this;
		}

		public final Builder withEmail(String email) {
			this.email = email;
			return this;
		}

		public final Builder withPhone(Optional<String> phone) {
			this.phone = phone;
			return this;
		}

		public final Builder withAddress(Optional<Address> address) {
			this.address = address;
			return this;
		}

		@Override
		public User build() {
			return validate(new User(this));
		}

		@Override
		public User get() {
			return build();
		}

		private static User validate(User instance) {
			checkNoBlanks(instance);
			return instance;
		}

	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public Optional<String> getPhone() {
		return phone;
	}

	public Optional<Address> getAddress() {
		return address;
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
		helper.add("email", email);
		helper.add("phone", phone);
		helper.add("address", address);
		return helper.toString();
	}

	@Override
	public int compareTo(User other) {
		ComparisonChain chain = ComparisonChain.start();
		chain = chain.compare(name, other.name);
		chain = chain.compare(email, other.email);
		chain = chain.compare(phone.orNull(), other.phone.orNull(), natural().nullsFirst());
		chain = chain.compare(address.orNull(), other.address.orNull(), natural().nullsFirst());
		return chain.result();
	}

}