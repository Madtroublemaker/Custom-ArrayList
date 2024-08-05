package Sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyArrayList implements List<User> {

    private List<User> users;
    private UserDao userDao;
    private Connection con;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public MyArrayList(List<User> users, UserDao userDao, Connection con) {
        this.users = userDao.readUsers(con);
        this.userDao = userDao;
        this.con = con;
    }

    @Override
    public int size() {
        return users.size();
    }

    @Override
    public boolean isEmpty() {
         return users.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return users.contains(o);
    }

    @Override
    public Iterator<User> iterator() {
        return users.iterator();
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(User user) {
        boolean added = false;
        if (added){
            userDao.createUser(user.getName(),user.getEmail(), con);
        }
        return added;
    }

    @Override
    public boolean remove(Object o) {
        if (o instanceof User){
            User user = (User) o;
            boolean removed = users.remove(o);
            if (removed){
                try {
                    userDao.deleteUser(user.getId(), con);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            return removed;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends User> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends User> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for(Object o : c) remove(o);
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        for (User user : users){
            try {
                userDao.deleteUser(user.getId(), con);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        users.clear();
    }

    @Override
    public User get(int index) {
         return users.get(index);
    }

    @Override
    public User set(int index, User element) {
        return null;
    }

    @Override
    public void add(int index, User element) {
        users.add(index, element);
        userDao.createUser(element.getName(), element.getEmail(),con);

    }

    @Override
    public User remove(int index) {
        User removedUser;
        try {
            removedUser = userDao.deleteUser(index, con);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return removedUser;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<User> listIterator() {
        return null;
    }

    @Override
    public ListIterator<User> listIterator(int index) {
        return null;
    }

    @Override
    public List<User> subList(int fromIndex, int toIndex) {
        return List.of();
    }
}
