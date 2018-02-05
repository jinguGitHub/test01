package com.itheima.mybatis.mapper;

import com.itheima.mybatis.pojo.User;

public interface UserMapper {
    /****
     * 根据id查询用户
     * @param id
     * @return
     */
    User queryUserById(Long id);

    /****
     * 新增用户
     * @param user
     * @return
     */
    int saveUser(User user);

    /****
     * 更新用户
     * @param user
     */
    void updateUserById(User user);

    /****
     * 根据id删除用户
     * @param id
     * @return
     */
    int deleteUserById(Long id);
}
