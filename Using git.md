#git commands summary (git 명령 정리)

##git의 환경 확인

$ git config --list
$ git config --global --list

##최초 글로벌 설정

$ git config --global user.name "Your Name"
$ git config --global user.email your.email@example.com
$ git config --global core.editor "sublime_text -w"
$ git config --global merge.tool vimdiff
$ git config --global color.ui true

글로벌 설정은 `~/.gitconfig`파일에 저장되므로 이 파일을 직접 편집해도 된다.

password 캐싱하기

With git 1.7.9 or later

$ git config --global credential.helper cache
$ git config --global credential.helper 'cache --timeout=3600'

if below git 1.8 <= ~ < 2.0

$ git config --global push.default simple

##change git diff tool with meld

$ git config --global diff.external meld
$ git diff filename
Usage: 
meld                        Start with an empty window
meld <file|dir>             Start a version control comparison
meld <file> <file> [<file>] Start a 2- or 3-way file comparison
meld <dir> <dir> [<dir>]    Start a 2- or 3-way directory comparison
meld <file> <dir>           Start a comparison between file and dir/file

meld: error: too many arguments (wanted 0-3, got 7)
external diff died, stopping at src/transferlistfilterswidget.h.

Meld will open but it will complain about bad parameters. The problem is that Git sends its external diff viewer seven parameters when Meld only needs two of them; two filenames of files to compare. One way to fix it is to write a script to format the parameters before sending them to Meld. Let's do that.

Create a new python script in your home directory (or wherever, it doesn't matter) and call it diff.py.

#!/usr/bin/python

import sys
import os

os.system('meld "%s" "%s"' % (sys.argv[2], sys.argv[5]))

Now we can set Git to perform it's diff on our new script (replacing the path with yours):

$ git config --global diff.external /home/username/bin/diff.py
$ git diff filename

##브랜치
###브랜치 만들기

$ git checkout -b {branch_name}

###브랜치 삭제

$ git branch -d {branch_name}
$ git branch -D {branch_name} # 강제 삭제

###브랜치 머지

$ git checkout master # master로 전환 후
$ git merge {branch_name_to_merge}

##원격저장소
### 원격저장소 보기

$ git remote -v

###로컬 브랜치를 새로 만든 후 원격저장소에 해당 브랜치를 push하고자 할 때

$ git push -u origin {branch_name}

원격 저장소에 해당 이름의 브랜치가 없다면 새로 생성된다.
-u 옵션은 향후에 수정된 내용을 push 혹은 pull하기 쉽게 할 수 있도록 하는 옵션이다.

###로컬저장소에는 없는 원격브랜치를 가져오고자 할 때

$ git fetch origin
$ git checkout --track origin/{branch_name}

fetch 명령으로 우선 원격 저장소에 변경사항을 가져온다. fetch해야만 새로운 브랜치가 추가 됐는 지를 확인할 수 있다. 두 번째 명령으로 원격 브랜치에 내용을 로컬브랜치로 생성하여 체크아웃한다.

###fetch tags from remote repo 원격저장소에서 태그 만 가져오기

$ git fetch --tags

위의 방법은 로컬태그가 non-fast-forward라는 에러 메시지가 떳을 때 해결할 수 있는 방법임

$ git push --tags
To https://github.com/onmoving/learning-django.git
! [rejected]        eof-creating-app -> eof-creating-app (non-fast-forward)
error: failed to push some refs to 'https://github.com/onmoving/learning-django.git'
To prevent you from losing history, non-fast-forward updates were rejected
Merge the remote changes (e.g. 'git pull') before pushing again.  See the
'Note about fast-forwards' section of 'git push --help' for details.

###checkout remote branch 원격 브랜치 체크아웃

$ git branch -a
* master
remotes/origin/HEAD -> origin/master
remotes/origin/bookmarks-app
remotes/origin/master
$ git checkout -b bookmarks-app origin/bookmarks-app
Branch bookmarks-app set up to track remote branch bookmarks-app from origin.
Switched to a new branch 'bookmarks-app'
$ git branch -a
* bookmarks-app
master
remotes/origin/HEAD -> origin/master
remotes/origin/bookmarks-app
remotes/origin/master

###add remote tag 원격 태그 추가

$ git tag local-tag-name
$ git push origin remote-tag-name

### push all local tags 로컬의 모든 태그를 한꺼번에 추가하기

$ git push --tags

[example]
$ git tag end-of-start-creating-app
$ git tag -l
end-of-start-creating-app
$ git push origin end-of-start-creating-app 
To https://github.com/onmoving/learning-django.git
* [new tag]         end-of-start-creating-app -> end-of-start-creating-app

###create tag 로컬 태그 생성
$ git tag tag-name

[example]
$ git tag end-of-start-creating-app
$ git tag -l
end-of-start-creating-app

###delete remote tag 원격 태그 삭제

git tag -d local-tag-name
git push origin :refs/tags/remote-tag-name
- or -
git push origin :tags/remote-tag-name


[example]
$ git push origin :refs/tags/end-of-start-creating-app
To https://github.com/onmoving/learning-django.git
- [deleted]         end-of-start-creating-app

###delete remote branch 원격 브랜치 삭제

git push origin :remote-branch-name

[example]
$ git push origin :start-creating-app
To https://github.com/onmoving/learning-django.git
- [deleted]         start-creating-app

##로깅

###로그 출력하기

한줄로 출력하기

$ git log --pretty=oneline
44babcb1f426d6ce880fd1796405b405e65af45f modified:   gcc_compiler/inside_of_comp
aaf9e94c38e1a0893268be1c3e793577dc36c09a modified:   ../gcc_compiler/compile_opt
c07679cd82b1a5f34cec52bff5e75cebf6a209e3 modified:   .gitignore modified:   READ
f63b7e3e89e88ebc0e9a094c6de2a3a85d011018 modified:   README.md modified:   binut
9e29a8631f6b42b2fb828033604361081c2b0582 modified:   binutils/README.md
a1ec640dc00981d04e12f6478083a94e096ba8f1 modified:   README.md new file:   binut
d9ef89e4954c62bba6bc335e3998cec0332be349 modified:   README.md new file:   binut
8181b452796172b0d231ea725a5ebf6f7990e993 modified:   README.md deleted:    vim/R
39b0dbcb161c241483298524daad26ce62cbb6a4 modified:   getting-started/README.md m
448276fc616ecd20ab48c6e7c8e6850696817b96 modified:   .gitignore modified:   gett
76e440d3c55e1dba89cb83f87fa172ba5c586a94 modified:   getting-started/autotools/R
6608ff81144769cdb42283db45e8ee8808812e11 modified:   getting-started/autotools/R
467536ad88b1c5d77589f5f6b91993e05d30e039 modified:   getting-started/autotools/R
f5241e23c8c71d942684546991bb1edf29a29b96 modified:   getting-started/autotools/R
b0b5dfca56abcffda2f28b97c3c8399ac774ed2e modified:   getting-started/autotools/R
b357978de8ef1fc1512615a52819142d18b15873 modified:   getting-started/autotools/R

##diff

$ git diff filename
$ git diff commit-hash filename

##Reference
- https://gist.github.com/onmoving