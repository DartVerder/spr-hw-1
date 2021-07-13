package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.repository.UserRepository;

@RequiredArgsConstructor
public class UserService {
  private final UserRepository repository;
}
