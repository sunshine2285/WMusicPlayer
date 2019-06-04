### servlet

---

* AudioSelectServlet

  * 作用：获取播放音乐的音频流

  * 请求参数：songID

  * 返回值：audioStream

    `response.setHeader("Content-Type", "audio/mpeg");`

* songSelectServlet

  * 作用：根据歌曲ID查询歌曲的具体信息

  * 请求参数：歌曲ID

  * 返回值：JSON
  
    |name |singer |length |coverUrl |audioUrl |
    | :--: | :--: | :--: | :--: | :--: |
    | 歌曲名称 | 歌手 |时常 |封面url |音频流url |

* SheetSelectServlet

  * 作用：获取歌单的详细信息及其包含的歌曲

  * 请求参数：sheetID

  * 返回值：JSON

    |   name   |     userID     | coverUrl |                  songlist                  | data             |
    | :------: | :------------: | :------: | :----------------------------------------: | ---------------- |
    | 歌单名称 | 歌单所有者的ID | 封面url  | 歌单中所有歌曲的ID，name，singer组成的列表 | 歌单最后更新日期 |

* SheetlistSelectServlet

  * 作用：获取所有歌单

  * 请求参数：NULL

  * 返回值：JSON 

    sheetlist，每一项：
    
    | sheetID |   name   | coverUrl |              topSonglist               |
    | :-----: | :------: | :------: | :------------------------------------: |
    | 歌单ID  | 歌单名称 | 歌单封面 | 歌单前三首歌曲的name，singer组成的列表 |

* IndexSelectServlet

  * 作用：获取填充首页所需要的信息

  * 请求参数：NULL

  * 返回值：JSON

    |                        bestSheetlist                         |                       newSonglist                        |                       hotSonglist                        |
    | :----------------------------------------------------------: | :------------------------------------------------------: | :------------------------------------------------------: |
    | 最受欢迎的前三个歌单列表，包含sheetID，name，coverUrl，topSonglist | 最新发布的前六首歌曲，包含songID，name，singer，coverUrl | 收听最多的前六首歌曲，包含songID，name，singer，coverUrl |

* UserLoginServlet

  * 作用：用户登录

  * 请求参数：userName，password

  * 返回值：JSON

    | errMsg           |
    | ---------------- |
    | "ok" /  "failed" |

* UserRegisterServlet

  * 作用：用户注册

  * 请求参数：userName，password，email

  * 返回值：JSON

    | errMsg           |
    | ---------------- |
    | "ok" /  "failed" |

* CommentSelectServlet

  * 作用：获取用户对歌曲的评价

  * 请求参数：songID

  * 返回值：JSON

    commentlist，每一项：
    
    | ID | userAvatarUrl | userName | content | date | thumbUp | thumbDown |
    | ------ | ------- | -------- | ------- | ---- | ------- | ---- |
    | 评论ID | 用户头像url | 用户名 | 评论内容 | 评论日期 | 点赞 | 反对 |

* CommentUpdateServlet

  * 作用：用户更新评论，点赞 / 反对 / 删除

  * 请求参数：commentID，mode（1/ -1/ 0 - 点赞 / 反对 / 删除）

  * 返回值：JSON

     | errMsg           |
    | ---------------- |
    | "ok" /  "failed" |

### model

---

* Song

  | ID   |name| singer | length | coverUrl | audioUrl | date   | hot |
  | ---- | :--|--- | ------ | -------- | -------- | ------ | ----- |
  | uint |string |string | uint   | string   | string   | string | uint  |

* Sheet
	
  | ID   | name   | userID | coverUrl | date   | hot  |
  | ---- | ------ | ------ | -------- | ------ | ---- |
  | uint | string | uint   | string   | string | uint |

* songlist

  | ID   | sheetID| songID |
  | ---- | ------ | ------ |
  | uint | uint | uint   |

* User

  | ID   | name   | avatarUrl |
  | ---- | ------ | --------- |
  | uint | string | string    |

* Comment

  | ID   |songID| userID | content | date   | thumbUp | thumbDown |
  | ---- |----| ------ | ------- | ------ | ------- | --------- |
  | uint | uint| uint   | string  | string | uint    | uint      |

### dao

---

* SongDao
* SheetDao
* songlistDao
* UserDao
* CommentDao

### config

---

* source.properties
  * source.audioBasePath
  * source.sheetCoverBasePath
  * source.songCoverBasePath
  * source.userAvatarBasePath
* jdbc.properties
  	* jdbc.url=jdbc:mysql://localhost:3306/webphoto?useUnicode=true&characterEncoding=utf-8
  	* jdbc.username=root
  	* jdbc.password=123456
  	* jdbc.driver=com.mysql.cj.jdbc.Driver
