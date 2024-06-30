package com.group.libraryapp.repository.user;

import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    // 파라미터로 DTO를 받는게 아니라 필요한 파리미터로 분리해서 받기
    public boolean isUserNotExist(long id){
        // 갱신할 레코드가 있는지 확인 후 있으면 갱신하도록 로직 변경
        String readSql = "SELECT * FROM user WHERE id = ?";

        // ? 에 getId를 넣고 해당 sql에 결과값이 있다면 뭐든 간에 0으로 간주한다
        // jdbcTemplat의 query의 반환값은 리스트임 그래서 해당 리스트가 비어있는지 아닌지 확인 하는 것 있으면 0이 들어있을거 없으면 암것도 없겠지
        return jdbcTemplate.query(readSql, (rs, rowNum) -> 0, id).isEmpty();
    }

    public void updateUserName(String name, long id){
        // 특정 id를 가진놈의 이름이 변경
        String sql = "UPDATE user set name = ? WHERE id = ?";
        jdbcTemplate.update(sql, name, id);
    }

    public boolean isUserNotExist(String name){
        String readSql = "SELECT * FROM user WHERE name = ?";
        return jdbcTemplate.query(readSql, (rs, rowNum) -> 0, name).isEmpty();

    }

    public void deleteUser(String name){
        String sql = "DELETE FROM user WHERE NAME =? ";
        jdbcTemplate.update(sql, name); // update는 갱신이 아니라 데이터에 변화가 있는 경우다
    }

    public void saveUser(String name, Integer age){
        String sql = "INSERT INTO user (name,age) VALUES (?,?)";
        jdbcTemplate.update(sql,name,age);
    }

    public List<UserResponse> getUsers(){
        String sql = "SELECT * FROM user";
//        return jdbcTemplate.query(sql, new RowMapper<UserResponse>() {
//            // 유저 정보를 UserResponse 타입으로 변환해주는 역할
//            @Override
//            public UserResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
//                long id = rs.getLong("id");
//                String name = rs.getString("name");
//                int age = rs.getInt("age");
//                return new UserResponse(id, name, age);
//            }
//        });

        // 람다로 변환
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            return new UserResponse(id, name, age);
        });
    }
}
