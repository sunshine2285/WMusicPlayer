### servlet

---

* AudioSelectServlet

  * 作用：获取播放音乐的音频流

  * 请求参数：songid

  * 返回值：audioStream

    `response.setHeader("Content-Type", "audio/mpeg");`

* songSelectServlet

  * 作用：根据歌曲id查询歌曲的具体信息

  * 请求参数：歌曲id

  * 返回值：JSON
  
    |name |singer |length |coverUrl |audioUrl |
    | :--: | :--: | :--: | :--: | :--: |
    | 歌曲名称 | 歌手 |时常 |封面url |音频流url |

* SheetSelectServlet

  * 作用：获取歌单的详细信息及其包含的歌曲

  * 请求参数：sheetid

  * 返回值：JSON

    |   name   |     userid     | coverUrl |                  songlist                  | data             |
    | :------: | :------------: | :------: | :----------------------------------------: | ---------------- |
    | 歌单名称 | 歌单所有者的id | 封面url  | 歌单中所有歌曲的id，name，singer组成的列表 | 歌单最后更新日期 |

* SheetlistSelectServlet

  * 作用：获取所有歌单

  * 请求参数：NULL

  * 返回值：JSON 

    sheetlist，每一项：
    
    | sheetid |   name   | coverUrl |              topSonglist               |
    | :-----: | :------: | :------: | :------------------------------------: |
    | 歌单id  | 歌单名称 | 歌单封面 | 歌单前三首歌曲的name，singer组成的列表 |

* IndexSelectServlet

  * 作用：获取填充首页所需要的信息

  * 请求参数：NULL

  * 返回值：JSON

    |                        bestSheetlist                         |                       newSonglist                        |                       hotSonglist                        |
    | :----------------------------------------------------------: | :------------------------------------------------------: | :------------------------------------------------------: |
    | 最受欢迎的前三个歌单列表，包含sheetid，name，coverUrl，topSonglist | 最新发布的前六首歌曲，包含songid，name，singer，coverUrl | 收听最多的前六首歌曲，包含songid，name，singer，coverUrl |

* UserLoginServlet

  * 作用：用户登录

    * 接受前端用户的code，调用微信服务端API通过code获取到用户的openid，
    * 通过openid判断是否存在用户信息，如果存在则返回
    * 如果不存在先向数据库内添加该用户的记录（仅添加openid），然后返回`userid=-1`表示用户未注册

  * 请求参数：code（通过wx.login()获得）

  * 返回值：JSON

    * 用户已注册：
    
    | userid | userName | avatarUrl     |
    | ------ | -------- | ------------- |
    | 用户id | 用户名   | 用户头像的url |
    
    * 用户未注册：
    
    | userid | userName | avatarUrl     |
    | ------ | -------- | ------------- |
    | -1 | null   | null |
    
    

* UserRegisterServlet

  * 作用：用户注册

    * 接受前端用户的code，调用微信服务端API通过code获取到用户的openid
    * 将用户的openid，userName，avatarUrl插入数据库
    * 根据用户的openid查得新用户的usedid返回前端

  * 请求参数：code（wx.login()获得），userName，avatarUrl

  * 返回值：JSON

    | userid                       |
    | ---------------------------- |
    | 返回用户注册成功之后的userid |

* CommentSelectServlet

  * 作用：获取用户对歌曲的评价

  * 请求参数：songid

  * 返回值：JSON

    commentlist，每一项：
    
    | id | userAvatarUrl | userName | content | date | thumbUp | thumbDown |
    | ------ | ------- | -------- | ------- | ---- | ------- | ---- |
    | 评论id | 用户头像url | 用户名 | 评论内容 | 评论日期 | 点赞 | 反对 |

* CommentUpdateServlet

  * 作用：用户更新评论，点赞 / 反对 / 删除

  * 请求参数：commentid，mode（1/ -1/ 0 - 点赞 / 反对 / 删除）

  * 返回值：JSON

     | errMsg           |
    | ---------------- |
    | "ok" /  "failed" |

### model

---

* Song

  | id   |name| singer | length | coverUrl | audioUrl | date   | hot |
  | ---- | :--|--- | ------ | -------- | -------- | ------ | ----- |
  | uint |string |string | uint   | string   | string   | string | uint  |

* Sheet
	
  | id   | name   | userid | coverUrl | date   | hot  |
  | ---- | ------ | ------ | -------- | ------ | ---- |
  | uint | string | uint   | string   | string | uint |

* SheetMap

  | id   | sheetid | songid |
  | :--- | :------ | :----- |
  | uint | uint    | uint   |

  

* User

  | id   | openid | name   | avatarUrl |
  | ---- | ------ | ------ | --------- |
  | uint | String | string | string    |

* Comment

  | id   | songid | userid | content | date   | thumbUp | thumbDown |
  | ---- | ------ | ------ | ------- | ------ | ------- | --------- |
  | uint | uint   | uint   | string  | string | uint    | uint      |

### dao

---

* SongDao

  * ```java
    public int insert(Song song) throws SQLException;
    ```

  * ```java
    public Song SelectById(int id) throws SQLException;
    ```

* SheetDao

  * ```java
    public int insert(Sheet sheet) throws SQLException;
    ```
    
  * ```java
     public Sheet selectById(int id) throws  SQLException;
    ```


* SheetMapDao

  * ```java
    public int insert(SheetMapDao sheetMapDao) throws SQLException;
    ```

  
  * ```java
    public int delete(int id, int mode) throws SQLException;
    ```

  * ```java
    public ArrayList<Integer> seletctBySheetId(int sheetid) throws SQLException;
    ```

  * ```java
    public int selectBySheetIdAndSongId(int sheetid, int songid) throws SQLException;
    ```

* UserDao

  * ```java
    public int insert(User user) throws SQLException;
    ```

  * ```java
    public User selectById(int id) throws SQLException;
    ```

  * ```java
    public int selectByOpenid(int openid) throws SQLException;
    ```

* CommentDao

  * ```java
    public int insert(Comment comment) throws SQLException;
    ```

  * ```java
     public int update(int id, int mode) throws SQLException;
    ```

  * ```java
    public int delete(int id) throws SQLException;
    ```

  * ```java
    public ArrayList<Comment> selectBySongid(int songid) throws SQLException;	
    ```

### filter

---

* EncodingFilter

### util

* DBUtil

### config

---

* source.properties
  
  > source.audioBasePath<br/>
  source.sheetCoverBasePath<br/>
  source.songCoverBasePath<br/>
  source.userAvatarBasePath
  
* jdbc.properties
  	
  >  jdbc.url=jdbc:mysql://localhost:3306/wmusic?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC<br/>
  jdbc.username=root<br/>
  jdbc.password= *** <br/>
  jdbc.driver=com.mysql.cj.jdbc.Driver

