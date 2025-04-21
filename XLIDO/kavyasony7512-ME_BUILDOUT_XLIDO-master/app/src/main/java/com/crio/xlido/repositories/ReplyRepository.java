package com.crio.xlido.repositories;

// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.Optional;
// import java.util.concurrent.atomic.AtomicLong;
// import com.crio.xlido.entities.Reply;

// public class ReplyRepository implements IReplyRepository {
//     private final Map<Long, Reply> storage = new HashMap<>();
//     private AtomicLong idCounter = new AtomicLong(0);

//     @Override
//     public Reply save(Reply entity) {
//         Reply reply = new Reply(idCounter.incrementAndGet(), entity);
//         storage.putIfAbsent(reply.getReplyId(), entity);
//         return reply;
//     }

//     @Override
//     public List<Reply> findAll() {
//         return new ArrayList<>(storage.values());
//     }

//     @Override
//     public Optional<Reply> findById(Long replyId) {
//         return Optional.ofNullable(storage.get(replyId));
//     }
    
// }
