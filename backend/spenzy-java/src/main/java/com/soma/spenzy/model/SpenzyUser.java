package com.soma.spenzy.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SpenzyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    @OneToMany(mappedBy = "user")
    private Set<Income> incomes = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Expense> expenses = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Subscription> subscriptions = new HashSet<>();
}
