// package hello.hellospring.repository;
// import hello.hellospring.domain.Test;
// import java.util.*;
// /**
//  * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
//  */
// public class MemoryTestRepository implements TestRepository {

//     private static Map<Long, Test> store = new HashMap<>();
//     private static long sequence = 0L;

//     @Override
//     public Test save(Test test) {

//         test.setNo(++sequence);
//         store.put(test.getNo(), test);

//         return test;
//     }

//     @Override
//     public Optional<Test> findByNo(Int no) {
//         return Optional.ofNullable(store.get(no));
//     }

//     @Override
//     public List<Test> findAll() {
//         return new ArrayList<>(store.values());
//     }

//     @Override
//     public Optional<Test> findByName(String name) {

//         return store.values().stream()
//                 .filter(test -> test.getName().equals(name))
//                 .findAny();
//     }

//     public void clearStore() {
//         store.clear();
//     }
// }