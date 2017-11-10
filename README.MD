# Using your starter kit

All starter kits should contain a `run_game.sh` and `run_game.bash`, you can use these scripts to quickly run a game of halite. By default, this script runs the basic __Settler__ bot against itself.

## Bot submission guidelines

Before submitting a bot, make sure you adhere to our guidelines, or the upload or compilation of your bot will fail.

1. You should have a `MyBot.{extension for language}` in the root folder of your zip. For Rust, this should be a `cargo.toml`
2. If you are building on top of starter kit provided by us, make sure to include the hlt folder.

## Uploading your bot

* Website: You can use the [play page](https://halite.io/play-programming-challenge) in the Halite website to submit your bot.
* Halite Client: If you want a command line experience, you can use the [Halite Client tool](https://halite.io/learn-programming-challenge/halite-cli-and-tools/halite-client-tools) to upload your bot.
* see below to use sbt tasks for creating the zipfile and uploading the bot.


# sbt additions

Two sbt-tasks are available
* `createZip`: Creates a distributable zip file containing MyBot.jar and the LANGUAGE file. This tasks doesn't need to be called manually before uploading the bot - sbt takes care of that.
* `uploadBot`: Uploads the bot using the `Halite Client tool` mentioned above. Make sure the hlt command is available in your PATH (or customize the command in the `build.sbt`) and you already authenticated yourself with the halite api.

## usage
* `sbt assembly`: creates the fat jar using the sbt-assembly plugin
* `sbt createZip`: see above
* `sbt uploadBot`: see above

These tasks depend on each other, meaning that `sbt uploadBot` first creates the fat-jar, zips it and finally uploads it.