/*package com.nahara.toka.repository;

import com.nahara.toka.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository("user")
public class UserAccess implements UserRepository{
    private static UserRepository userRepos= new UserRepository() {
        @Override
        public <S extends User> S save(S s) {
            return null;
        }

        @Override
        public <S extends User> Iterable<S> saveAll(Iterable<S> iterable) {
            return null;
        }

        @Override
        public Optional<User> findById(Long aLong) {
            return Optional.empty();
        }

        @Override
        public boolean existsById(Long aLong) {
            return false;
        }

        @Override
        public Iterable<User> findAll() {
            return null;
        }

        @Override
        public Iterable<User> findAllById(Iterable<Long> iterable) {
            return null;
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void deleteById(Long aLong) {

        }

        @Override
        public void delete(User user) {

        }

        @Override
        public void deleteAll(Iterable<? extends User> iterable) {

        }

        @Override
        public void deleteAll() {

        }

        @Override
        public User findByUsername(String username) {
            return null;
        }
    };
        @Override
        public User findByUsername(String username) {

            return userRepos.findByUsername(username);
        }

        @Override
        public <S extends User> S save(S s) {
            return null;
        }

        @Override
        public <S extends User> Iterable<S> saveAll(Iterable<S> iterable) {
            return null;
        }

        @Override
        public Optional<User> findById(Long aLong) {
           return userRepos.findById(aLong);
           // return Optional.empty();
        }

        @Override
        public boolean existsById(Long aLong) {
            return false;
        }

        @Override
        public Iterable<User> findAll() {
            return null;
        }

        @Override
        public Iterable<User> findAllById(Iterable<Long> iterable) {
            return null;
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void deleteById(Long aLong) {

        }

        @Override
        public void delete(User user) {

        }

        @Override
        public void deleteAll(Iterable<? extends User> iterable) {

        }

        @Override
        public void deleteAll() {

        }

}*/