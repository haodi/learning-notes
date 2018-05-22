Mac 学习笔记
=============

### How can I permanently add my SSH private key to Keychain so it is automatically available to ssh?

    On OSX, the native ssh-add client has a special argument to save the private key's passphrase in the OSX keychain, which means that your normal login will unlock it for use with ssh. On OSX Sierra and later, you also need to configure SSH to always use the keychain (see Step 2 below).
    
    Alternatively you can use a key without a passphrase, but if you prefer the security that's certainly acceptable with this workflow.
    
   #### Step 1 - Store the key in the keychain
    Just do this once:
    
    ssh-add -K ~/.ssh/[your-private-key]
    
    Enter your key passphrase, and you won't be asked for it again.
    
    (If you're on a pre-Sierra version of OSX, you're done, Step 2 is not required.)
    
   #### Step 2 - Configure SSH to always use the keychain
    It seems that OSX Sierra removed the convenient behavior of persisting your keys between logins, and the update to ssh no longer uses the keychain by default. Because of this, you will get prompted to enter the passphrase for a key after you upgrade, and again after each restart.
    
    The solution is fairly simple, and is outlined in this github thread comment. Here's how you set it up:
    
   ##### 1. Ensure you've completed Step 1 above to store the key in the keychain.
    
   ##### 2. If you haven't already, create an ~/.ssh/config file. In other words, in the .ssh directory in your home dir, make a file called config.
    
   ##### 3. In that .ssh/config file, add the following lines:
    
    Host *
      UseKeychain yes
      AddKeysToAgent yes
      IdentityFile ~/.ssh/id_rsa
    Change ~/.ssh/id_rsa to the actual filename of your private key. If you have other private keys in your ~.ssh directory, also add an IdentityFile line for each of them. For example, I have one additional line that reads IdentityFile ~/.ssh/id_ed25519 for a 2nd private key.
    
    The UseKeychain yes is the key part, which tells SSH to look in your OSX keychain for the key passphrase.
    
   ##### 4. That's it! Next time you load any ssh connection, it will try the private keys you've specified, and it will look for their passphrase in the OSX keychain. No passphrase typing required.