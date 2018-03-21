package com.taotao.manager.controller;

import com.taotao.manager.pojo.Item;
import com.taotao.manager.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Jeff Huang
 * @version 1.0
 * @description com.taotao.manager.controller
 * @date 2018/3/3
 */
@Controller
@RequestMapping(value = "item/interface")
public class ItemInterfaceController {

    @Autowired
    private ItemService itemService;

    // http://manager.taotao.com/rest/item/interface/{id}
    /**
     * 查询
     * @param id
     * @return 返回的类型是ResponseEntity，泛型声明为需要返回的数据类型
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Item> queryItemById(@PathVariable("id")Long id){
        try {
            Item item = itemService.queryById(id);
            //查询成功,响应成功的状态码200
                //简单做法
            //return ResponseEntity.ok(item);
                //可以设置HttpStatus枚举的OK
            return ResponseEntity.status(HttpStatus.OK).body(item);
        } catch (Exception e) {
            e.printStackTrace();

        }
        //如果有异常,设置500状态码
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }


    /**
     *  //2.新增
     * @param item
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
        public ResponseEntity<Void> saveItem(Item item){
        try {
            itemService.save(item);
            //新增成功,新增返回状态码201
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //如果有异常,返回500异常状态码
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    /**
     * //3.(修改)更新
     * @param item
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> updateItemById(Item item){
        try {
            itemService.updateByIdSelective(item);
            //更新成功,返回成功状态码204
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //如果有异常,返回500异常状态码
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    /**
     * //4.删除
     * @param id
     * @return
     */
    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteItemById(@PathVariable("id")Long id){
        try {
            itemService.deleteById(id);
            //删除成功,返回删除成功的状态码204
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //如果有异常,返回500异常状态码
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
