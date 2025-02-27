package de.metaphoriker.pathetic.api.util;

/**
 * Represents a supplier that accepts a parameter.
 */
@FunctionalInterface
public interface ParameterizedSupplier<T> {

  T accept(T value);
}
