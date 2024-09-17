package com.svetanis.ood.onlinereader.model;

import static com.google.common.base.MoreObjects.toStringHelper;
import static com.google.common.base.Optional.absent;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Ordering.natural;
import static com.svetanis.java.base.Objects.equalByComparison;
import static com.svetanis.java.base.Optionals.lcase;
import static com.svetanis.java.base.Strings.lcase;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.validate.Validation.checkNoBlanks;
import static java.util.Arrays.asList;
import static java.util.Objects.hash;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects.ToStringHelper;
import com.google.common.base.Optional;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.ImmutableList;
import com.svetanis.java.base.ProvidingBuilder;
import com.svetanis.java.base.validate.IgnoreValidation;

@JsonDeserialize(builder = Book.Builder.class)
public final class Book implements Comparable<Book> {

	private final String isbn;
	private final String title;
	private final Optional<String> subject;
	private final Optional<String> publisher;
	private final Optional<String> language;
	private final Optional<Integer> pages;
	private final ImmutableList<Author> authors;
	@IgnoreValidation
	private final int hash;

	private Book(Builder builder) {
		this.isbn = lcase(builder.isbn);
		this.title = lcase(builder.title);
		this.subject = lcase(builder.subject);
		this.publisher = lcase(builder.publisher);
		this.language = lcase(builder.language);
		this.pages = builder.pages;
		this.authors = newList(builder.authors);
		this.hash = hash(isbn, title, subject, publisher, language, pages, authors);
	}

	public static final Book build(String isbn, String title, Author author) {
		return build(isbn, title, asList(author));
	}

	public static final Book build(String isbn, String title, Iterable<Author> authors) {
		Builder builder = builder();
		builder.withIsbn(isbn);
		builder.withTitle(title);
		builder.withAuthors(newList(authors));
		return builder.build();
	}

	public static final Builder builder() {
		return new Builder();
	}

	public static class Builder implements ProvidingBuilder<Book> {

		private String isbn;
		private String title;
		private Optional<String> subject = absent();
		private Optional<String> publisher = absent();
		private Optional<String> language = absent();
		private Optional<Integer> pages = absent();
		private List<Author> authors = newArrayList();

		public final Builder withIsbn(String isbn) {
			this.isbn = isbn;
			return this;
		}

		public final Builder withTitle(String title) {
			this.title = title;
			return this;
		}

		public final Builder withSubject(Optional<String> subject) {
			this.subject = subject;
			return this;
		}

		public final Builder withPublisher(Optional<String> publisher) {
			this.publisher = publisher;
			return this;
		}

		public final Builder withLanguage(Optional<String> language) {
			this.language = language;
			return this;
		}

		public final Builder withPages(Optional<Integer> pages) {
			this.pages = pages;
			return this;
		}

		public final Builder withAuthors(List<Author> authors) {
			this.authors = authors;
			return this;
		}

		@Override
		public Book build() {
			return validate(new Book(this));
		}

		@Override
		public Book get() {
			return build();
		}

		private static Book validate(Book instance) {
			checkNoBlanks(instance);
			checkArgument(instance.getAuthors().size() > 0, "at least 1 author is required, found 0 instead");
			return instance;
		}

	}

	public String getIsbn() {
		return isbn;
	}

	public String getTitle() {
		return title;
	}

	public Optional<String> getSubject() {
		return subject;
	}

	public Optional<String> getPublisher() {
		return publisher;
	}

	public Optional<String> getLanguage() {
		return language;
	}

	public Optional<Integer> getPages() {
		return pages;
	}

	public ImmutableList<Author> getAuthors() {
		return authors;
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
		helper.add("isbn", isbn);
		helper.add("title", title);
		helper.add("subject", subject);
		helper.add("publisher", publisher);
		helper.add("language", language);
		helper.add("pages", pages);
		helper.add("authors", authors);
		return helper.toString();
	}

	@Override
	public int compareTo(Book other) {
		ComparisonChain chain = ComparisonChain.start();
		chain = chain.compare(isbn, other.isbn);
		chain = chain.compare(title, other.title);
		chain = chain.compare(subject.orNull(), other.subject.orNull(), natural().nullsFirst());
		chain = chain.compare(publisher.orNull(), other.publisher.orNull(), natural().nullsFirst());
		chain = chain.compare(language.orNull(), other.language.orNull(), natural().nullsFirst());
		chain = chain.compare(pages.orNull(), other.pages.orNull(), natural().nullsFirst());
		chain = chain.compare(authors, other.authors, natural().lexicographical());
		return chain.result();
	}

}