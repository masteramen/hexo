if (!window.location.href.match(/localhost:3888/))
  window.location =
    "http://localhost:3888/hello?url=" +
    encodeURIComponent(window.location.href) +
    (document.querySelector("meta[name='fileName']")
      ? "&fileName=" +
        encodeURIComponent(
          document
            .querySelector("meta[name='fileName']")
            .getAttribute("content")
        )
      : "");
