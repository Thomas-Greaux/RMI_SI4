package server;

import client.ClientSession;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;


public class ServerTopicImpl extends UnicastRemoteObject implements ServerTopic {

    private List<ClientSession> clients;

    private List<ClientSession> toRemove;

    protected ServerTopicImpl(int port) throws RemoteException {
        super(port);

        this.toRemove = new ArrayList<>();
        this.clients = new ArrayList<>();
    }

    @Override
    public boolean login(ClientSession a) throws RemoteException {
        System.out.println("New Connection established with " + a.getName() + "\n");

        publish(a.getName() + " just joined the topic\n");

        a.tell("You just joined the topic");

        clients.add(a);

        return true;
    }

    @Override
    public void publish(String toSend) throws RemoteException {
        for (ClientSession session :
                this.clients) {
            try {
                session.tell(toSend);
            } catch (Exception e){
                this.toRemove.add(session);
            }
        }

        this.remove();
    }

    private void remove() {
        this.clients.removeAll(this.toRemove);

        this.toRemove.clear();
    }
}
