#!/usr/bin/env bash

eval "$(ssh-agent -s)" # Start ssh-agent cache
chmod 600 .travis/id_rsa # TODO(Private-key for ssh): Allow read access to the private key
ssh-add .travis/id_rsa # TODO(Private-key for ssh): Add the private key to SSH

git config --global push.default matching
git remote add deploy $REMOTE_VM_DEPLOY_SSH
git push deploy master

echo "Partial deployment is implemented"