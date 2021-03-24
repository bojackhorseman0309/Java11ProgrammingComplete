/*
 * Copyright (C) 2021 aloar
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package labs.pm.data;

import java.io.Serializable;
import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;

import java.time.LocalDate;
import java.util.Objects;

import static labs.pm.data.Rating.NOT_RATED;

/**
 * {@code Product} class represents properties and behaviours of product objects
 * in the Product Management System.
 * <br>
 * Each Product has an id, name and price
 * <br>
 * Each product can have a discount, calculated based on a
 * {@link DISCOUNT_RATE discount rate}
 *
 * @author aloar
 * @version 4.0
 */
public abstract class Product implements Rateable<Product>, Serializable {

    private int id;
    private String name;
    private BigDecimal price;
    private Rating rating;

    Product(int id, String name, BigDecimal price, Rating rating) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
    }

    Product(int id, String name, BigDecimal price) {
        this(id, name, price, NOT_RATED);
    }

//    Product() {
//        this(0, "no name", BigDecimal.ZERO);
//    }
    /**
     * A constant that defines a {@link java.math.BigDecimal BigDecimal} value
     * of the discount rate
     * <br>
     * Discount Rate is 10%
     */
    private static final BigDecimal DISCOUNT_RATE = BigDecimal.valueOf(0.1);

    public int getId() {
        return id;
    }

    //    public void setId(int id) {
//        this.id = id;
//    }
    public String getName() {
        return name;
    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

    public BigDecimal getPrice() {
        return price;
    }

//    public void setPrice(BigDecimal price) {
//        this.price = price;
//    }

    /**
     * Caluclates a discount based on a product price and
     * {@link DISCOUNT_RATE discount rate}
     *
     * @return a {@link java.math.BigDecimal BigDecimal} value of the discount
     */
    public BigDecimal getDiscount() {
        return price.multiply(DISCOUNT_RATE).setScale(2, HALF_UP);
    }

    @Override
    public Rating getRating() {
        return rating;
    }

    //    public abstract Product applyRating(Rating newRating);
//    {
//        return new Product(id, name, price, newRating);
//    }
    public LocalDate getBestBefore() {
        return LocalDate.now();
    }

    @Override
    public String toString() {
        return id + ", " + name + ", " + price + ", " + getDiscount() + ", " + rating.getStars() + ", " + getBestBefore();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Product) {
            final Product other = (Product) obj;
            return this.id == other.id; // && Objects.equals(this.name, other.name);
        }
        return false;
    }

}
