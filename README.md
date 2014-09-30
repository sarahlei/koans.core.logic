# Clojure core.logic koans

Koans are a fun and easy way to learn - no experience assumed or
required. Just follow the instructions below to start making tests

### Running the Koans

To run the koans, simply run `lein koan run`. As you save your files
with the correct answers, the runner will automatically advance you to
the next koan or file.

When you execute `run` you'll see something like this:

    Problem in  /home/self/code/koans.core.logic/src/koans/basics.clj
    ---------------------
    Assertion failed!
    java.lang.Exception: [LINE 3] What is always untrue, shall yield no answer.
    (= (run* [q] u#) __)

The output is telling you that you have a failing test in
`src/koans/basics.clj`. Open that file up and make it pass! In
general, you just fill in the blanks to make tests pass. Sometimes
there are several (or even an infinite number) of correct answers: any
of them will work in these cases.

The koans differ from normal TDD in that the tests are already written
for you, so you'll have to pay close attention to the failure
messages, because up until the very end, making a test pass just means
that the next failure message comes up.

While it might be easy (especially at first) to just fill in the
blanks making things pass, you should work thoughtfully, making sure
you understand why the answer is what it is. Enjoy your path to
enlightenment!

### License

Copyright (C) 2014 Gergely Nagy <algernon@madhouse-project.org>

Distributed under the [GNU Lesser General Public License][lgpl],
version 3 or (at your opinion) any later version.

 [lgpl]: http://www.gnu.org/licenses/lgpl.html
