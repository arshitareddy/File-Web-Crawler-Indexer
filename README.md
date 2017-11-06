File & Web - Crawler & Indexer

The FileCrawler will crawl from directory specified(in the fictitious file-system) to end-point of it and queues all the directories, files and links it encounters.
Later the FileIndexer, will index all the elements in the queue that FileCrawler fills. Here the Indexing is Caching the queue contents and if the Chache is full the cache contents are replaced on FIFO logic. 
The WebCrawler will take the input, an URL in String form and connects to the webpage and get body in to a document and parse for absolute hrefs(URLs/Links), and calls itself on that parsed URLs(links), if the URL is visited once, they are skipped(not wanting the repeted data). Every time the URL is connected, the HTML body is read and saved in to a new file, i.e Indexing.
All the instances of FileCrawler, FileIndexer and WebCrawler are running on Multiple Threads using the Executor Frame Work
The HTML files are downloaded and saved into the Base directory of the project.

Implemented using Java.

Would require Jsoup jarfiles
