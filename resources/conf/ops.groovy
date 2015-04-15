import org.rioproject.config.Constants

deployment(name: 'Test') {
    groups System.getProperty(Constants.GROUPS_PROPERTY_NAME, System.getProperty('user.name'))

    service(name: 'Outrigger') {
        interfaces {
            classes "net.jini.space.JavaSpace"
            resources 'test/outrigger-dl-2.2.2.jar'
        }
        implementation(class: "com.sun.jini.outrigger.TransientOutriggerImpl") {
            resources 'test/outrigger-2.2.2.jar'
            resources 'test/outrigger-dl-2.2.2.jar'
            resources 'test/jsk-dl-2.2.2.jar'
        }
        maintain 1
        maxPerMachine 1
    }

}
