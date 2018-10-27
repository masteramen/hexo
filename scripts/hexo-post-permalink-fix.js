/**
 * Fix post permalink not overriding config permalink in Hexo v3.2.2
 * Add a `mypermalink: hello-example` to any post front-matter to override config.
 * Place this script in your project/scripts directory (project/scripts/hexo-post-permalink-fix.js)
 */

"use strict";

const fs = require("hexo-fs");
const Promise = require("bluebird");

const glob = require("glob");
const path = require("path");

function postsAssetGenerater(locals) {
  const self = this;

  function process(name) {
    return glob
      .sync("./source/_posts/**/**")
      .filter(file => !/(.*)\.md$/.test(file) && fs.lstatSync(file).isFile())
      .map(file => {
        const source = file;
        let path = file.split("source/_posts/")[1];
        const data = {
          modified: false
        };

        data.data = () => fs.createReadStream(source);

        return {
          path,
          data
        };
      });
  }

  return Promise.all([process()]).then(data =>
    Array.prototype.concat.apply([], data)
  );
}
hexo.extend.generator.register("postsAsset", postsAssetGenerater);
//let time = new Date().getTime();

// 处理 post 中图片
/*hexo.extend.processor.register("posts/*path", function(file) {
  if (file.type == "delete") return file;
  if (file.source.endsWith(".md") || file.source.endsWith(".markdown")) return;
  if (!/^_posts\//.test(file.path)) return;



 hexo.route.set(file.path.replace(/^_posts\//,''), {
    data: function(){
      return () => fs.createReadStream(file.source); 
    },
    modified: false
});


  if(false)hexo.extend.generator.register((time++)+"a", function(locals) {
    console.log('test...')
    console.log(file.path.replace(/^_posts\//, ""))
    return {
      path: file.path.replace(/^_posts\//, ""),
      data: function() {
        return fs.createReadStream(file.source);
      }
    };
  });
});*/

hexo.extend.filter.register(
  "post_permalink",
  function(data) {
    // if (typeof data.mypermalink != "undefined") {
    // unregister default post_permalink filter that doesn't respect custom post permalink
    hexo.extend.filter.unregister(
      "post_permalink",
      require("hexo/lib/plugins/filter/post_permalink")
    );
    // hacky way to bypass an error due to newly missing filter above
    hexo.extend.filter.register("post_permalink", function(data) {
      return data;
    });

    if (data.slug.endsWith(".html")) {
      return decodeURIComponent(data.slug);
    }
    if (data.fileName) {
      return data.date.format("YYYY") + "/" + data.fileName + "/";
    }
    //}
    return data;
  },
  9
);

//将相对地址转为绝对地址
hexo.extend.filter.register("before_post_render", function(data) {
  var dir_post = path.join(this.source_dir, data.source);
  var pattern = /!\[(.*?)\]\((.*?)\)/g;
  data.content = data.content.replace(pattern, (match, alt, src) => {
    let path_img = path.resolve(dir_post, "..", src);
    let src_new = path_img
      .replace(this.source_dir + "_posts", "")
      .replace(/\\/g, "/");

    return `![${alt}](${src_new})`;
  });

  return data;
});

/*hexo.extend.filter.register(
  "before_post_render",
  function(data) {
    data.env = this.env.env;
    console.log("env:");
    console.log(data.env);

    return data;
  },
  15
);*/
